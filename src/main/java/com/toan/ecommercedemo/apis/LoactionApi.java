package com.toan.ecommercedemo.apis;

import java.util.List;

import com.toan.ecommercedemo.model.dto.LocationDto;
import com.toan.ecommercedemo.services.DistrictService;
import com.toan.ecommercedemo.services.ProvinceService;
import com.toan.ecommercedemo.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "*", maxAge = -1)
public class LoactionApi {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private VillageService villageService;

    @GetMapping("/getAllProvince")
    public @ResponseBody
    List<LocationDto> getAllProvince() {
        return provinceService.getAll();
    }

    @GetMapping("/getDistrictByProvince")
    public @ResponseBody
    List<LocationDto> getDistrictByProvince(@RequestParam("provinceId") String provinceId) {
        return districtService.getByProvince(provinceId);
    }

    @GetMapping("/getVillageByDistrict")
    public @ResponseBody
    List<LocationDto> getVillageByDistrict(@RequestParam("districtId") String districtId) {
        return villageService.getByDistrict(districtId);
    }

}
