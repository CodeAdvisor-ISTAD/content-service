package co.istad.content_service.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BasedResponse<T> {
    private Integer code;
    private T payload;
}
