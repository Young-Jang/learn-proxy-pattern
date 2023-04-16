package hello.proxy.aop.exam.dto;

import hello.proxy.aop.exam.annotation.NotNull;
import lombok.Builder;

public class ExamDto {
    @NotNull
    private String param1;

    private String param2;

    @Builder
    public ExamDto(String p1, String p2){
        this.param1 = p1;
        this.param2 = p2;
    }
}
