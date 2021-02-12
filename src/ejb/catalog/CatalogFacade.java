package ejb.catalog;

import java.util.Date;
import java.util.Collection;

import javax.ejb.Local;

import jpa.*;

import domain.*;


@Local
public interface CatalogFacade<T> {
	 
	
	  public void addCategory(String name, String parent);
	  public void deleteCategory(Integer categoryId);
	  public void updateCategory(Integer categoryId, String name, String parent);
	  public Collection<?> listCatalogElements(String name);
	  public Collection<CategoryJPA> listAllCatalogElements();
	  public void addBrand(String name);
	  public void updateBrand(Integer brandId, String name);
	  public Collection<BrandJPA> listAllBrands();
	  
	  
	  public boolean addProduct(CategoryJPA category, String name, BrandJPA brand, ModelJPA model, MoneyV2 dailyPrice, String description);
	  public boolean deleteProduct(Integer id);
	  public boolean updateProduct(Integer productId, String name, CategoryJPA category, BrandJPA brand, ModelJPA model, MoneyV2 dailyPrice, String description);
	  public Collection<ProductJPA> listCategoryProducts(String name);
	  public Collection<ProductJPA> listAllProducts();
	  public ProductJPA getProductInformation(Integer productId);
	  
	  public boolean addItem(ProductJPA product, String serialNumber, ItemStatus status);
	  public boolean deleteItem(String serialNumber);
	  public boolean updateProductItem(ProductJPA product, String serialNumber, ItemStatus status);
	  public Collection<ItemJPA> listProductItems(Integer productId);
	  public Collection<ItemJPA> listAllProductItems();
	  public Collection<ItemJPA> listProductAvailableItems(Integer productId, Date from, Date to);
	  
	  public boolean addModel(String name);
	  public boolean updateModel(Integer modelId, String name);
	  public Collection<ModelJPA> listAllModels();
	  
}
