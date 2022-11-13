package app.example.demo.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("分页与查询")
public class ResultPaginationBean<T, O> {

    @ApiModelProperty("开始记录索引")
    private int start;
    @ApiModelProperty("当前页码")
    private int pageNum;
    @ApiModelProperty("每页条数")
    private int pageSize;
    @ApiModelProperty("数据总条数")
    private int totalSize;
    @ApiModelProperty("总页数")
    private int totalPages;
    @ApiModelProperty("查询参数")
    private O options;
    @ApiModelProperty("返回的数据")
    private List<T> data;

    public int getStart() {
        if (pageNum == 0) {
            pageNum = 1;
        }
        start = this.getPageSize() * (pageNum - 1);
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setStart(int start) {
        this.start = start;
    }

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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public O getOptions() {
        return options;
    }

    public void setOptions(O options) {
        this.options = options;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
