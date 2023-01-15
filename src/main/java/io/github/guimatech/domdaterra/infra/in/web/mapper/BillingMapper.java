package io.github.guimatech.domdaterra.infra.in.web.mapper;

import io.github.guimatech.domdaterra.domain.billing.Billing;
import io.github.guimatech.domdaterra.infra.in.web.dto.BillingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static io.github.guimatech.domdaterra.shared.util.MathUtil.moneyBRLToDouble;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    BillingMapper INSTANCE = Mappers.getMapper(BillingMapper.class);

    @Mapping(source = "amountDue", target = "amountDue", qualifiedByName = "moneyBRLToDouble")
    @Mapping(source = "interestProjection", target = "interestProjection", qualifiedByName = "moneyBRLToDouble")
    @Mapping(source = "interestValue", target = "interestValue", qualifiedByName = "moneyBRLToDouble")
    @Mapping(source = "approximateTotalAmount", target = "approximateTotalAmount", qualifiedByName = "moneyBRLToDouble")
    @Mapping(source = "phone", target = "phone", qualifiedByName = "numberOnly")
    Billing requestToDomain(BillingRequest billingRequest);

    @Named("moneyBRLToDouble")
    static Double moneyToDouble(String value) {
        return moneyBRLToDouble(value);
    }

    @Named("numberOnly")
    static String numberOnly(String value) {
        return value.replaceAll("[^\\d.]", "");
    }
}
