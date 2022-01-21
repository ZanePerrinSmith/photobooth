package com.appleindustries.photobooth.bootstrap;

import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.enums.PhotoPackageEnum;
import com.appleindustries.photobooth.repositories.PhotoPackageRepository;
import com.appleindustries.photobooth.services.CustomerService;
import com.appleindustries.photobooth.services.PhotoPackageService;
import com.appleindustries.photobooth.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zane
 */
@Component
@AllArgsConstructor
public class PhotoBoothJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CustomerService customerService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    PhotoPackageService photoPackageService;

    @Autowired
    PhotoPackageRepository photoPackageRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadPhotoPackages();
        loadCustomers();
        loadPurchases();
    }

    private void loadPurchases() {
        List<Customer> customers = (List<Customer>) customerService.listAll();
        List<PhotoPackage> photoPackages = new ArrayList<>();
        photoPackages.add(photoPackageRepository.findByType(PhotoPackageEnum.PRINT));
        for (Customer customer : customers) {
            Purchase purchase = new Purchase();
            purchase.setCustomer(customer);
            // purchase.setPhotoPackage(photoPackageRepository.findByType(PhotoPackageEnum.PRINT));
        }
    }


    private void loadCustomers() {
        Customer customer1 = new Customer(new ArrayList<>(), "Zane", "Smith", "zps@gmail.com");
        customerService.saveOrUpdate(customer1);
        Customer customer2 = new Customer(new ArrayList<>(), "Robby", "Keene", "rk@gmail.com");
        customerService.saveOrUpdate(customer2);
        Customer customer3 = new Customer(new ArrayList<>(), "Miguel", "Diaz", "md@eaglefang.com");
        customerService.saveOrUpdate(customer3);
        Customer customer4 = new Customer(new ArrayList<>(), "Daniel", "LaRusso", "dl@myagido.com");
        customerService.saveOrUpdate(customer4);
        Customer customer5 = new Customer(new ArrayList<>(), "Johnny", "Lawrence", "jl@eaglefang.com");
        customerService.saveOrUpdate(customer5);
    }

    private void loadPhotoPackages() {
//        PhotoPackage photoPackage1 = new PhotoPackage(PhotoPackageEnum.PRINT, new BigDecimal(5), null);
//        photoPackageService.saveOrUpdate(photoPackage1);
//        PhotoPackage photoPackage2 = new PhotoPackage(PhotoPackageEnum.PANORAMA, new BigDecimal(7), null);
//        photoPackageService.saveOrUpdate(photoPackage2);
//        PhotoPackage photoPackage3 = new PhotoPackage(PhotoPackageEnum.STRIPS, new BigDecimal(5), null);
//        photoPackageService.saveOrUpdate(photoPackage3);
    }
}
