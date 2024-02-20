package api.send.email.dto;

public record EmailDTO(
        String from,
        String to,
        String message,
        String subject,
        String attachment
) {}
