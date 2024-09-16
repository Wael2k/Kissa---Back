package PFA.project.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TemplateConstants {
    EMAIL_TEMPLATE("email-template.ftl"),
    EMAIL_CONFIRMATION_ACCOUNT_TEMPLATE("email-confirmation-account-template.ftl"),
    EMAIL_CONFIRMATION_CLIENT_TEMPLATE("email-confirmation-client-template.ftl"),
    VERIFY_EMAIL("verify-mail-template");

    private final String value;
}
