package br.com.renner.domain.validations;

import br.com.renner.domain.ports.input.ValidacoesInPort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class ValidacoesAdapter implements ValidacoesInPort {

    @Autowired
    private Validator validator;

    @Override
    public Optional<StringBuilder> execute(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {

            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Object> constraintViolation : violations) {
                sb.append(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage() + "/");
            }
            Optional<StringBuilder> optionalStringBuilder = Optional.of(sb);

            return optionalStringBuilder;
        }
        return Optional.empty();
    }
}