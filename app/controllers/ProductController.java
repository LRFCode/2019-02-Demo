package controllers;

import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ProductController extends Controller
{
    private FormFactory formFactory;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Inject
    public ProductController(FormFactory formFactory, ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.formFactory = formFactory;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String searchName = form.get("searchName");

        List<ProductDetail> products = productRepository.getDetailList(searchName);

        return ok(views.html.Products.render(products));
    }

    @Transactional(readOnly = true)
    public Result getEditProduct(int productId)
    {
        Product product = productRepository.get(productId);
        List<Category> categories = categoryRepository.getList();
        return ok(views.html.EditProduct.render(product, categories));
    }

    @Transactional
    public Result postEditProduct(int productId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String productName = form.get("productName");
        int categoryId = Integer.parseInt(form.get("categoryId"));

        Product product = productRepository.get(productId);

        product.setProductName(productName);
        product.setCategoryId(categoryId);

        return ok("Saved");
    }
}








