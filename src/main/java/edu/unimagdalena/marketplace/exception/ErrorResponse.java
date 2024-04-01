package edu.unimagdalena.marketplace.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String exception;
    private String path;
    private List<String> messages;
}
