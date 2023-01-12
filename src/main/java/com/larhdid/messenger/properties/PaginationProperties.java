package com.larhdid.messenger.properties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaginationProperties {
    @Value("${pageable.number.element:/10}")
    private int pageSize;
}