package app.example.demo.dto.response;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final int NO_PERMESSION = 2;
    private String msg = "SUCESS";
    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public static <T> ResultBean success(T data) {
        ResultBean<Object> resultBean = new ResultBean<>();
        resultBean.setMsg("success");
        resultBean.setCode(ResultBean.SUCCESS);
        resultBean.setData(data);
        return resultBean;
    }

}
