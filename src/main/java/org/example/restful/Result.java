package org.example.restful;

public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMsg(ResultCode resultCode) {
        this.msg = resultCode.getMessage();
    }

    public Object getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result success(T data) {
        Result result = new Result(ResultCode.SUCCESS, data);
        return result;
    }

    public static <T> Result success(String msg) {
        Result result = new Result(ResultCode.SUCCESS, (Object)null);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result success(T data, String msg) {
        Result result = new Result(ResultCode.SUCCESS, data);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static Result fail(ResultCode resultCode) {
        Result result = new Result(resultCode);
        return result;
    }

    public static Result fail(ResultCode resultCode, String msg) {
        Result result = new Result(resultCode);
        result.setMsg(msg);
        return result;
    }

    public static Result create(ResultCode resultCode, Object data, String msg) {
        Result result = new Result(resultCode);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
}