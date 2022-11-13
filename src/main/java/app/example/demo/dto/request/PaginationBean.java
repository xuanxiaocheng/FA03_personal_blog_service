package app.example.demo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("分页查询")
public class PaginationBean<T, O> {

    @ApiModelProperty("当前页码")
    private int pageNum;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("查询参数")
    private O options;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public O getOptions() {
        return options;
    }

    public void setOptions(O options) {
        this.options = options;
    }


}
