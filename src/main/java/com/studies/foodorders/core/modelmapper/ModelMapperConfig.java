package com.studies.foodorders.core.modelmapper;

import com.studies.foodorders.api.model.localization.address.AddressModel;
import com.studies.foodorders.api.model.restaurant.RestaurantModel;
import com.studies.foodorders.domain.models.localization.Address;
import com.studies.foodorders.domain.models.restaurant.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper =  new ModelMapper();

        /*modelMapper.createTypeMap(Restaurant.class, RestaurantModel.class)
        			.addMapping(Restaurant::getShippingCosts, RestaurantModel::setShippingCosts);*/

        var addressToAddressModelTypeMap = modelMapper.createTypeMap(
                Address.class, AddressModel.class);

        addressToAddressModelTypeMap.<String>addMapping(
                addressSrc -> addressSrc.getCity().getState().getName(),
                (addressModelDestiny, value) -> addressModelDestiny.getCity().setState(value));

        return modelMapper;

    }

}
