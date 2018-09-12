package com.taxation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.taxation.dao.interfaces.RoleRepository;
import com.taxation.model.PropertyType;
import com.taxation.model.PropertyUsage;
import com.taxation.model.Role;
import com.taxation.model.RoleName;
import com.taxation.model.Tax;
import com.taxation.resource.SeedAllResponse;
import com.taxation.service.interfaces.IPropertyTypeService;
import com.taxation.service.interfaces.IPropertyUsageService;
import com.taxation.service.interfaces.ISeedService;
import com.taxation.service.interfaces.ITaxService;

public class SeedService implements ISeedService {

    @Autowired
    IPropertyTypeService propertyTypeService;

    @Autowired
    IPropertyUsageService propertyUsageService;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ITaxService taxService;

    @Override
    public SeedAllResponse seedAll() {

        List<PropertyType> defaultPropertyTypes = new ArrayList<>();
        PropertyType propertyType1 = new PropertyType( "KAWELU");
        defaultPropertyTypes.add(propertyType1);
        PropertyType propertyType2 = new PropertyType("MUD HOUSE");
        defaultPropertyTypes.add(propertyType2);
        PropertyType propertyType3 = new PropertyType("HALF MUD HOUSE");
        defaultPropertyTypes.add(propertyType3);
        PropertyType propertyType4 = new PropertyType("CONCRETE HOUSE");
        defaultPropertyTypes.add(propertyType4);
        propertyTypeService.saveAll(defaultPropertyTypes);


        List<PropertyUsage> defaultPropertyUsages = new ArrayList<>();
        PropertyUsage propertyUsage1 = new PropertyUsage("FAMILY");
        defaultPropertyUsages.add(propertyUsage1);
        PropertyUsage propertyUsage2 = new PropertyUsage("SHOP");
        defaultPropertyUsages.add(propertyUsage2);
        PropertyUsage propertyUsage3 = new PropertyUsage("AGRICULTURE");
        defaultPropertyUsages.add(propertyUsage3);
        PropertyUsage propertyUsage4 = new PropertyUsage("ANIMAL HUSBANDRY");
        defaultPropertyUsages.add(propertyUsage4);
        PropertyUsage propertyUsage5 = new PropertyUsage("WAREHOUSE");
        defaultPropertyUsages.add(propertyUsage5);
        PropertyUsage propertyUsage6 = new PropertyUsage("OTHERS");
        defaultPropertyUsages.add(propertyUsage6);
        propertyUsageService.saveAll(defaultPropertyUsages);

        List<Tax> defaultTaxes = new ArrayList<>();
        Tax tax1 = new Tax("HOUSE",(float)50);
        defaultTaxes.add(tax1);
        Tax tax2 = new Tax("WATER",(float)100);
        defaultTaxes.add(tax2);
        taxService.saveAll(defaultTaxes);
        
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleName.ROLE_USER));
        roles.add(new Role(RoleName.ROLE_ADMIN));        
        roleRepository.saveAll(roles);

        SeedAllResponse seedAllResponse = new SeedAllResponse(defaultPropertyTypes,defaultPropertyUsages,defaultTaxes,roles);
        return seedAllResponse;
    }

    @Override
    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeService.getAllPropertyTypes();
    }

    @Override
    public List<PropertyUsage> getAllPropertyUsages() {
        return propertyUsageService.getAllPropertyUsages();
    }


}

//        --insert into property_usage values(1,'FAMILY');
//        --insert into property_usage values(2,'SHOP');
//        --insert into property_usage values(3,'AGRICULTURE');
//        --insert into property_usage values(4,'ANIMAL HUSBANDRY');

//--insert into property_usage values(1,'FAMILY');
//        --insert into property_usage values(2,'SHOP');
//        --insert into property_usage values(3,'AGRICULTURE');
//        --insert into property_usage values(4,'ANIMAL HUSBANDRY');