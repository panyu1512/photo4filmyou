package ejb.catalog;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import domain.ItemStatus;
import domain.MoneyV2;
import jpa.BrandJPA;
import jpa.CategoryJPA;
import jpa.ItemJPA;
import jpa.ModelJPA;
import jpa.ProductJPA;

@Stateless
public class CatalogFacadeBean<T> implements CatalogFacadeRemote<T>, CatalogFacade<T> {

	Logger logger = Logger.getLogger(CatalogFacadeBean.class.getName());

	// Persistence Unit Context
	@PersistenceContext(unitName = "PDS2_PhotoFilm4You")
	private EntityManager entman;

	@Resource
	private SessionContext context;

	@Override
	public void addCategory(String name, String parent) 
	{
		CategoryJPA parentCategory = new CategoryJPA();
		
		try{
			parentCategory = (CategoryJPA) entman.createNamedQuery(CategoryJPA.FIND_BY_NAME).setParameter("name", parent).getSingleResult();
		} 
		catch (NoResultException exception){
			logger.info("La cateogoria no existe");
			parentCategory = null;
		}
		
		
			
		CategoryJPA category = new CategoryJPA(name,parentCategory);
		try{		 
			entman.persist(category);
		}catch (PersistenceException exception){
			logger.severe(exception.getMessage());
			context.setRollbackOnly();
		}
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		CategoryJPA cat = new CategoryJPA();
		
		try {
			cat = entman.find(CategoryJPA.class, categoryId);
			logger.info("Entro en cat: " + cat);
			entman.remove(cat);	
		}
		catch (NoResultException exception){
			logger.info("La cateogoria no existe");
		} 

	}

	@Override
	public void updateCategory(Integer categoryId, String name, String parent) {

		CategoryJPA parentCategory = new CategoryJPA();
		CategoryJPA category = new CategoryJPA();
		
		try {
			
			try{
				parentCategory = (CategoryJPA) entman.createNamedQuery(CategoryJPA.FIND_BY_NAME).setParameter("name", parent).getSingleResult();
			} 
			catch (NoResultException exception){
				logger.info("La cateogoria no existe");
				parentCategory = null;
			}
			category = entman.find(CategoryJPA.class, categoryId);
			category.setName(name);
			category.setParent(parentCategory);
			try {
				entman.merge(category);
			}catch (PersistenceException exception){
				logger.info("Error al realizar el update");
			} 
		}
		catch (NoResultException exception){
			logger.info("La cateogoria no existe");
		} 

	}

	@Override
	public boolean addProduct(CategoryJPA category, String name, BrandJPA brand, ModelJPA model, MoneyV2 dailyPrice,
			String description) {

		ProductJPA product = new ProductJPA(category, name, brand, model, dailyPrice, description);

		try {
			entman.persist(product);
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteProduct(Integer id) {
		ProductJPA product = this.entman.find(ProductJPA.class, id);

		if (product == null) {
			return false;
		}

		this.entman.remove(product);

		try {
			this.entman.flush();
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateProduct(Integer productId, String name, CategoryJPA category, BrandJPA brand, ModelJPA model,
			MoneyV2 dailyPrice, String description) {
		ProductJPA product = this.entman.find(ProductJPA.class, productId);

		if (product == null) {
			return false;
		}

		product.setName(name);
		product.setCategory(category);
		product.setBrand(brand);
		product.setModel(model);
		product.setDailyPrice(dailyPrice);
		product.setDescription(description);

		try {
			this.entman.persist(product);
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public Collection<ProductJPA> listCategoryProducts(String name) {
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

		CriteriaQuery<ProductJPA> query = criteriaBuilder.createQuery(ProductJPA.class);
		Root<ProductJPA> root = query.from(ProductJPA.class);

		ParameterExpression<String> nameParameter = criteriaBuilder.parameter(String.class);

		query.select(root).where(criteriaBuilder.equal(root.get("name"), nameParameter));

		return this.entman.createQuery(query).setParameter(nameParameter, name).getResultList();

	}
	
	@Override
	public Collection<ProductJPA> listAllProducts(){
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

		CriteriaQuery<ProductJPA> query = criteriaBuilder.createQuery(ProductJPA.class);
		Root<ProductJPA> root = query.from(ProductJPA.class);

		query.select(root);

		return this.entman.createQuery(query).getResultList();
	}


	@Override
	public ProductJPA getProductInformation(Integer productId) {
		return this.entman.find(ProductJPA.class, productId);
	}

	@Override
	public boolean addItem(ProductJPA product, String serialNumber, ItemStatus status) {
		ItemJPA item = new ItemJPA(product, serialNumber, status);

		try {
			entman.persist(item);
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}

		return true;
	}


	@Override
	public boolean deleteItem(String serialNumber) {
		ItemJPA item = this.entman.find(ItemJPA.class, serialNumber);

		if (item == null) {
			return false;
		}

		this.entman.remove(item);

		try {
			this.entman.flush();
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}
		return true;
	}


	@Override
	public boolean updateProductItem(ProductJPA product, String serialNumber, ItemStatus status) {
		ItemJPA item = this.entman.find(ItemJPA.class, serialNumber);

		if (item == null) {
			return false;
		}

		item.setProduct(product);
		item.setStatus(status);

		try {
			this.entman.persist(item);
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}
		return true;
	}


	@Override
	public Collection<ItemJPA> listProductItems(Integer productId) {
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

		CriteriaQuery<ItemJPA> query = criteriaBuilder.createQuery(ItemJPA.class);
		Root<ItemJPA> root = query.from(ItemJPA.class);

		ParameterExpression<Integer> idParameter = criteriaBuilder.parameter(Integer.class);

		query.select(root).where(criteriaBuilder.equal(root.get("productId"), idParameter));

		return this.entman.createQuery(query).setParameter(idParameter, productId).getResultList();
	}
	
	@Override
	public Collection<ItemJPA> listAllProductItems(){
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

		CriteriaQuery<ItemJPA> query = criteriaBuilder.createQuery(ItemJPA.class);
		Root<ItemJPA> root = query.from(ItemJPA.class);

		query.select(root);

		return this.entman.createQuery(query).getResultList();
	}


	@Override
	public Collection<ItemJPA> listProductAvailableItems(Integer productId, Date from, Date to) {
		return this.entman.createNativeQuery("select * from PDS2_PhotoFilm4You.item i where i.idproduct = :productId and status = 'Operational' and i.serialnumber not in" + 
				"		(select rt.items_serialnumber from PDS2_PhotoFilm4You.rent_item rt where rt.rentjpa_id in (select r.id from PDS2_PhotoFilm4You.rent r where" + 
				"																														(r.fromdate between :dateFrom and :dateTo" + 
				"																														or r.todate between :dateFrom and :dateTo)))", ItemJPA.class)
				.setParameter("productId", productId)
                .setParameter("dateFrom", from)
                .setParameter("dateTo", to)
                .getResultList();
	}

	@Override
	public boolean addModel(String name) {
		ModelJPA model = new ModelJPA(name);

		try {
			entman.persist(model);
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}

		return true;
	}

	@Override
	public boolean updateModel(Integer modelId, String name) {
		ModelJPA model = this.entman.find(ModelJPA.class, modelId);

		if (model == null) {
			return false;
		}

		model.setName(name);

		try {
			this.entman.merge(model);
		} catch (PersistenceException exception) {
			context.setRollbackOnly();
			return false;
		}
		return true;
	}

	@Override
	public Collection<ModelJPA> listAllModels() {

		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

		CriteriaQuery<ModelJPA> query = criteriaBuilder.createQuery(ModelJPA.class);
		Root<ModelJPA> root = query.from(ModelJPA.class);

		query.select(root);

		return this.entman.createQuery(query).getResultList();
	}

	@Override
	public Collection<?> listCatalogElements(String name) {
		Collection<CategoryJPA> categorias = null;
		
		CategoryJPA parentCategory = new CategoryJPA();
		
		try{
			parentCategory = (CategoryJPA) entman.createNamedQuery(CategoryJPA.FIND_BY_NAME).setParameter("name", name).getSingleResult();
		} 
		catch (NoResultException exception){
			logger.info("La cateogoria no existe");
			//parentCategory.setName("Sin parent");
			return null;
		}
		
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();
		CriteriaQuery<CategoryJPA> query = criteriaBuilder.createQuery(CategoryJPA.class);
		Root<CategoryJPA> root = query.from(CategoryJPA.class);
		
		ParameterExpression<CategoryJPA> parentParameter = criteriaBuilder.parameter(CategoryJPA.class);;
		
		query.select(root).where(criteriaBuilder.equal(root.get("parent"), parentParameter));
		
		categorias = this.entman.createQuery(query).setParameter(parentParameter, parentCategory).getResultList();
		
		categorias.add(parentCategory);
		
		return categorias;
	}
	
	@Override
	public Collection<CategoryJPA> listAllCatalogElements(){
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();

		CriteriaQuery<CategoryJPA> query = criteriaBuilder.createQuery(CategoryJPA.class);
		Root<CategoryJPA> root = query.from(CategoryJPA.class);

		query.select(root);

		return this.entman.createQuery(query).getResultList();
	}


	@Override
	public void addBrand(String name) {
		
		BrandJPA brand = new BrandJPA();
		brand.setName(name);
		try{		 
			entman.persist(brand);
		}catch (PersistenceException exception){
			logger.severe(exception.getMessage());
			context.setRollbackOnly();
		}

	}

	@Override
	public void updateBrand(Integer brandId, String name) {
		BrandJPA brand = new BrandJPA();
		
		try {
			brand = entman.find(BrandJPA.class, brandId);
			brand.setName(name);
			try {
				entman.merge(brand);
			}catch (PersistenceException exception){
				logger.info("Error al realizar el update");
			} 
		}
		catch (NoResultException exception){
			logger.info("La cateogoria no existe");
		} 

	}

	@Override

	public Collection<BrandJPA> listAllBrands() {
		logger.info("entramos");
		CriteriaBuilder criteriaBuilder = this.entman.getCriteriaBuilder();
		CriteriaQuery<BrandJPA> query = criteriaBuilder.createQuery(BrandJPA.class);
		query.select(query.from(BrandJPA.class));
		List<BrandJPA> listBrand = entman.createQuery(query).getResultList();
		return listBrand;
	}

}
