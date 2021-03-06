package MyAnnotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ConstraintValidatorForInteger.class,ConstraintValidatorForList.class })
public @interface MultipleOfThree {
    String message() default "必须是3的倍数";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
