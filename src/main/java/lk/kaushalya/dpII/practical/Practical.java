package lk.kaushalya.dpII.practical;

/**
 * Question:
 * Chain of Responsibility design pattern gives more than one object an opportunity to handle a request by
 * linking receiving objects together. Write a program to demonstrate the model of the servlet filter mechanism
 * for the following example using the Chain of Responsibility design pattern.
 */


class Request {

    private String url;
    private String parameter;

    public Request(String url, String parameter) {
        this.url = url;
        this.parameter = parameter;
    }

    public String getUrl() {
        return url;
    }

    public String getParameter() {
        return parameter;
    }
}

abstract class ServletFilter {
    private ServletFilter ServletFilter;

    public ServletFilter getServletFilter() {
        return ServletFilter;
    }

    public void setServletFilter(ServletFilter servletFilter) {
        ServletFilter = servletFilter;
    }

    abstract void requestFilter(Request request);
}

// validate the request url .jsp
class JSPValidationFilter extends ServletFilter{

    @Override
    void requestFilter(Request request) {

        if(request.getUrl().endsWith(".jsp")){
            System.out.println("\nJSPValidationFilter: Valid request url: " + request.getUrl());
            System.out.println("JSPValidationFilter: Request is accepted.\n");

            if(this.getServletFilter() != null){
                this.getServletFilter().requestFilter(request);
            }

        }else{
            System.out.println("\nJSPValidationFilter: Invalid request url: " + request.getUrl());
            System.out.println("JSPValidationFilter: Request is rejected.");
            System.out.println("JSPValidationFilter: Request is rejected due to invalid url.");
        }


    }
}

// validate the request parameter contains username and password
class ParameterNameValidationFilter extends ServletFilter{

    @Override
    void requestFilter(Request request) {
        if(request.getParameter().contains("username") && request.getParameter().contains("password")){
            System.out.println("ParameterNameValidationFilter: Valid request parameter: " + request.getParameter());
            System.out.println("ParameterNameValidationFilter: Request is accepted.\n");

            if(this.getServletFilter() != null){
                this.getServletFilter().requestFilter(request);
            }

        }else{
            System.out.println("ParameterNameValidationFilter: Invalid request parameter: " + request.getParameter());
            System.out.println("ParameterNameValidationFilter: Request is rejected.");
            System.out.println("ParameterNameValidationFilter: Request is rejected due to missing username or password.");
        }
    }
}

// validate the request parameter value contains username and password
class ParameterValueValidationFilter extends ServletFilter{

    @Override
    void requestFilter(Request request) {
        String paramString = request.getParameter();

        String username = "";
        String password = "";

        if (paramString != null && !paramString.isEmpty()) {
            String[] pairs = paramString.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    if (keyValue[0].equals("username")) {
                        username = keyValue[1];
                    } else if (keyValue[0].equals("password")) {
                        password = keyValue[1];
                    }
                }
            }
        }

        // Exactly check if username is "abc" and password is "123"
        if(username.equals("abc") && password.equals("123")){
            System.out.println("ParameterValueValidationFilter: Valid request parameter value: " + request.getParameter());
            System.out.println("ParameterValueValidationFilter: Request is accepted.\n");

            System.out.println("Request is accepted and processed successfully.");
            System.out.println("Request URL: " + request.getUrl());
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

        }else {
            System.out.println("ParameterValueValidationFilter: Invalid request parameter value: " + request.getParameter());
            System.out.println("ParameterValueValidationFilter: Request is rejected.");
            System.out.println("ParameterValueValidationFilter: Request is rejected due to invalid username or password.");
        }
    }
}

public class Practical {
    public static void main(String[] args) {
        Request request1 = new Request("www.cor.com/index.jsp", "username=abc&password=123");
        Request request2 = new Request("www.cor.com/log.jsp", "name=kaushaly&age=24");
        Request request3 = new Request("www.cor.com/home.html", "username=abc&password=123");
        Request request4 = new Request("www.cor.com/login.jsp", "username=kaushaly&password=1234");

        JSPValidationFilter jspValidationFilter = new JSPValidationFilter();
        ParameterNameValidationFilter nameValidationFilter = new ParameterNameValidationFilter();
        ParameterValueValidationFilter valueValidationFilter = new ParameterValueValidationFilter();

        jspValidationFilter.setServletFilter(nameValidationFilter);
        nameValidationFilter.setServletFilter(valueValidationFilter);

        jspValidationFilter.requestFilter(request1);
        System.out.println("--------------------------------------------------");
        jspValidationFilter.requestFilter(request2);
        System.out.println("--------------------------------------------------");
        jspValidationFilter.requestFilter(request3);
        System.out.println("--------------------------------------------------");
        jspValidationFilter.requestFilter(request4);
        System.out.println("--------------------------------------------------");
    }
}
