package com.oliver.controller.am;


import com.oliver.entity.am.AmProduct;
import com.oliver.entity.am.AmProductDAO;
import com.oliver.entity.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * com.oliver.controller.am.AmProductController
 *
 * @author oliver
 * @date 2020/1/8 16:30
 */
@RestController
@RequestMapping("am/pro")
public class AmProductController {

    @Resource
    private AmProductDAO productDAO;

    @GetMapping("list")
    public Object getAllAmPro() {
        return ResultVO.success(productDAO.selectAll());
    }

    @PostMapping("{amid}/{proid}")
    public Object bindAmPro(@PathVariable("amid") long amid, @PathVariable("proid") long proid) {
        return ResultVO.error("");
    }

    @GetMapping("/{id}/{name}/{price}")
    public Object queryByName(@PathVariable("id") long id, @PathVariable("name") String name, @PathVariable("price") BigDecimal price) {
        AmProduct product = new AmProduct();
        product.setId(id);
        product.setProductName(name);
        product.setProductPrice(price);
        return ResultVO.success(productDAO.queryByObject(product));
    }
}
