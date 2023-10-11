package com.studies.foodorders.core.modelmapper;

import com.studies.foodorders.api.v1.models.localization.address.AddressModel;
import com.studies.foodorders.api.v1.models.order.OrderItemInput;
import com.studies.foodorders.api.v2.models.localization.city.CityInputV2;
import com.studies.foodorders.domain.models.localization.Address;
import com.studies.foodorders.domain.models.localization.City;
import com.studies.foodorders.domain.models.order.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper =  new ModelMapper();

        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        /*modelMapper.createTypeMap(Restaurant.class, RestaurantModel.class)
        			.addMapping(Restaurant::getShippingCosts, RestaurantModel::setShippingCosts);*/

        var addressToAddressModelTypeMap = modelMapper.createTypeMap(
                Address.class, AddressModel.class);

        addressToAddressModelTypeMap.<String>addMapping(
                addressSrc -> addressSrc.getCity().getState().getName(),
                (addressModelDestiny, value) -> addressModelDestiny.getCity().setState(value));

        modelMapper.createTypeMap(CityInputV2.class, City.class)
                .addMappings(mapper -> mapper.skip(City::setId));

        return modelMapper;

    }

}
