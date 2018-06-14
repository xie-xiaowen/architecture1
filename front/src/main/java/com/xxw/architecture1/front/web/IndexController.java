package com.xxw.architecture1.front.web;

import com.xxw.architecture1.cartmgr.service.CartService;
import com.xxw.architecture1.cartmgr.service.ICartService;
import com.xxw.architecture1.cartmgr.vo.CartModel;
import com.xxw.architecture1.cartmgr.vo.CartQueryModel;
import com.xxw.architecture1.goodsmgr.service.IGoodsService;
import com.xxw.architecture1.goodsmgr.vo.GoodsModel;
import com.xxw.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.xxw.architecture1.ordermgr.service.IOrderDetailService;
import com.xxw.architecture1.ordermgr.service.IOrderService;
import com.xxw.architecture1.ordermgr.vo.OrderDetailModel;
import com.xxw.architecture1.ordermgr.vo.OrderModel;
import com.xxw.architecture1.ordermgr.vo.OrderQueryModel;
import com.xxw.architecture1.storemgr.service.IStoreService;
import com.xxw.architecture1.storemgr.vo.StoreModel;
import com.xxw.pageutil.Page;
import com.xxw.util.format.DateFormatHelper;
import net.sf.ehcache.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class IndexController {

    @Autowired
    private IGoodsService goodsService; // 商品
    @Autowired
    private ICartService cartService;   // 购物车
    @Autowired
    private IOrderService orderService; // 主订单
    @Autowired
    private IOrderDetailService orderDetailService; // 子订单
    @Autowired
    private IStoreService storeService;  // 库存

    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public String toIndex(Model model){
        GoodsQueryModel qm = new GoodsQueryModel();
        qm.getPage().setPageShow(100);
        Page<GoodsModel> page = goodsService.getByConditionPage(qm);
        model.addAttribute("page", page);

        return "index";
    }

    @RequestMapping(value = "/toGoodsDesc/{goodsUuid}", method = RequestMethod.GET)
    public String toDesc(@PathVariable("goodsUuid")int goodsUuid, Model model){
        GoodsModel gm = goodsService.getByUuid(goodsUuid);
        model.addAttribute("m", gm);
        return "goods/desc";
    }

    @RequestMapping(value = "/addToCart/{goodsUuid}", method = RequestMethod.GET)
    public String addToCart(@PathVariable("goodsUuid")int goodsUuid, @CookieValue("MyLogin") String myLogin){
        int customerUuid = Integer.valueOf(myLogin.split(",")[0]);
        CartModel cart = new CartModel();
        cart.setBuyNum(1);
        cart.setGoodsUuid(goodsUuid);
        cart.setCustomerUuid(customerUuid);
        cartService.create(cart);
        return "redirect:/toCart";
    }

    @RequestMapping(value = "/toCart", method = RequestMethod.GET)
    public String toCart(@CookieValue("MyLogin") String myLogin, Model model){
        int customerUuid = Integer.valueOf(myLogin.split(",")[0]);
        CartQueryModel cqm = new CartQueryModel();
        cqm.setCustomerUuid(customerUuid);
        cqm.getPage().setPageShow(100);
        Page<CartModel> page = cartService.getByConditionPage(cqm);
        model.addAttribute("page", page);
        return "/cart/myCart";
    }

    /**
     * 提交订单
     * @param myLogin
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(@CookieValue("MyLogin")String myLogin){
        // 1.获取当前用户UUID
        int customerUuid = Integer.valueOf(myLogin.split(",")[0]);
        // 查询当前用户购物车中所有商品
        CartQueryModel cqm = new CartQueryModel();
        cqm.getPage().setPageShow(10000);
        cqm.setCustomerUuid(customerUuid);
        Page<CartModel> page = cartService.getByConditionPage(cqm);
        // 2.计算订单总价钱
        float totalMoney = 0.0f;
        for (CartModel cartModel : page.getResult()) {
            totalMoney += 10.0;       // 数据库中么有price字段，此处模拟每个商品价钱为10.0
        }
        // 3.生成总订单
        OrderModel om = new OrderModel();
        om.setCustomerUuid(customerUuid); // 用户UUID
        om.setOrderTime(DateFormatHelper.longToStr(System.currentTimeMillis())); // 下单时间
        om.setSaveMoney(0.0f);        // 优惠总金额
        om.setTotalMoney(totalMoney); // 订单总金额
        om.setState(1);
        orderService.create(om);
        // 4.获取总订单ID
        OrderQueryModel oqm = new OrderQueryModel();
        oqm.setOrderTime(om.getOrderTime());
        Page<OrderModel> order = orderService.getByConditionPage(oqm);
        om = order.getResult().get(0);
        // 5.生成子订单，关联总订单
        for (CartModel cartModel : page.getResult()) {
            OrderDetailModel odm = new OrderDetailModel();
            odm.setGoodsUuid(cartModel.getGoodsUuid());
            odm.setMoney(10.0f);
            odm.setOrderNum(1);
            odm.setOrderUuid(om.getUuid());
            odm.setPrice(10.0f);
            odm.setSaveMoney(0.0f);
            orderDetailService.create(odm);

            // 6.更新库存
            StoreModel sm = storeService.getOrderNumByGoodsUuid(cartModel.getGoodsUuid());
            sm.setStoreNum(sm.getStoreNum() - cartModel.getBuyNum());
            storeService.update(sm);

            // 7.清空购物车
            cartService.delete(cartModel.getUuid());
        }
        return "success";
    }

}
