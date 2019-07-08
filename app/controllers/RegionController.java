package controllers;

import models.Region;
import models.RegionRepository;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class RegionController extends Controller
{
    private RegionRepository regionRepository;
    private FormFactory formFactory;

    @Inject
    public RegionController(RegionRepository regionRepository, FormFactory formFactory)
    {
        this.regionRepository = regionRepository;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getList()
    {
        List<Region> regions = regionRepository.getList();

        return ok(views.html.Regions.render(regions));
    }

    public Result getAddRegion()
    {
        return ok(views.html.AddRegion.render());
    }

    @Transactional
    public Result postAddRegion()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        Region region = new Region();
        String regionName = form.get("regionName");
        region.setRegionName(regionName);
        regionRepository.add(region);

        return redirect(routes.RegionController.getList());
    }

    @Transactional(readOnly=true)
    public Result getEditRegion(int regionId)
    {
        Region region = regionRepository.get(regionId);

        return ok(views.html.EditRegion.render(region));
    }

    @Transactional()
    public Result postEditRegion(int regionId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String regionName = form.get("regionName");

        Region region = regionRepository.get(regionId);
        region.setRegionName(regionName);

        return redirect(routes.RegionController.getList());
    }

}










