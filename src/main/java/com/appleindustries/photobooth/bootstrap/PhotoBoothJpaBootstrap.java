package com.appleindustries.photobooth.bootstrap;

import com.appleindustries.photobooth.entities.Customer;
import com.appleindustries.photobooth.entities.PhotoPackage;
import com.appleindustries.photobooth.entities.Purchase;
import com.appleindustries.photobooth.entities.PurchaseDetail;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zane
 */
@Component
@AllArgsConstructor
public class PhotoBoothJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PhotoPackageService photoPackageService;

    @Autowired
    private PhotoPackageRepository photoPackageRepository;

    /**
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadPhotoPackages();
        loadCustomers();
        loadPurchases();
    }

    private void loadPurchases() {
        List<Customer> customers = (List<Customer>) customerService.listAll();
        PhotoPackage photoPackage = photoPackageRepository.findByType(PhotoPackageEnum.PRINT);
        for (Customer customer : customers) {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setQuantity(1);
            purchaseDetail.setPhotoPackage(photoPackage);

            Purchase purchase = new Purchase();
            customer.addPurchase(purchase);
            purchase.addPurchaseDetail(purchaseDetail);
            purchaseService.saveOrUpdate(purchase);
        }
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Zane");
        customer1.setLastName("Smith");
        customer1.setEmail("zps@gmail.com");
        customerService.saveOrUpdate(customer1);
        Customer customer2 = new Customer();
        customer2.setFirstName("Daniel");
        customer2.setLastName("LaRusso");
        customer2.setEmail("dl@myagi.com");
        customerService.saveOrUpdate(customer2);
        Customer customer3 = new Customer();
        customer3.setFirstName("Johnny");
        customer3.setLastName("Lawrence");
        customer3.setEmail("jl@eaglefang.com");
        customerService.saveOrUpdate(customer3);
        Customer customer4 = new Customer();
        customer4.setFirstName("John");
        customer4.setLastName("Kreese");
        customer4.setEmail("jk@cobrakai.com");
        customerService.saveOrUpdate(customer4);
        Customer customer5 = new Customer();
        customer5.setFirstName("Terrance");
        customer5.setLastName("Silver");
        customer5.setEmail("ts@cobrakai.com");
        customerService.saveOrUpdate(customer5);
        Customer customer6 = new Customer();
        customer6.setFirstName("Chozen");
        customer6.setLastName("Toguchi");
        customer6.setEmail("ct@myagi.com");
        customerService.saveOrUpdate(customer6);
        Customer customer7 = new Customer();
        customer7.setFirstName("Miguel");
        customer7.setLastName("Diaz");
        customer7.setEmail("md@cobrakai.com");
        customerService.saveOrUpdate(customer7);
        Customer customer8 = new Customer();
        customer8.setFirstName("Eli");
        customer8.setLastName("Moskowitz");
        customer8.setEmail("hawk@myagi.com");
        customerService.saveOrUpdate(customer8);
        Customer customer9 = new Customer();
        customer9.setFirstName("Tory");
        customer9.setLastName("Nichols");
        customer9.setEmail("tn@cobrakai.com");
        customerService.saveOrUpdate(customer9);
        Customer customer10 = new Customer();
        customer10.setFirstName("Samantha");
        customer10.setLastName("LaRusso");
        customer10.setEmail("sl@myagi.com");
        customerService.saveOrUpdate(customer10);
    }

    private void loadPhotoPackages() {
        PhotoPackage photoPackage1 = new PhotoPackage();
        photoPackage1.setType(PhotoPackageEnum.PRINT);
        photoPackage1.setPrice(new BigDecimal(5));
        photoPackageService.saveOrUpdate(photoPackage1);
        PhotoPackage photoPackage2 = new PhotoPackage();
        photoPackage2.setType(PhotoPackageEnum.PANORAMA);
        photoPackage2.setPrice(new BigDecimal(7));
        photoPackageService.saveOrUpdate(photoPackage2);
        PhotoPackage photoPackage3 = new PhotoPackage();
        photoPackage3.setType(PhotoPackageEnum.STRIPS);
        photoPackage3.setPrice(new BigDecimal(5));
        photoPackageService.saveOrUpdate(photoPackage3);
    }
}
