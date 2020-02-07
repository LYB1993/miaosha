package com.oliver.controller.am;

import com.oliver.entity.am.AmProduct;
import com.oliver.entity.am.AmSeckill;
import com.oliver.entity.am.AmSeckillProduct;
import com.oliver.entity.am.AmSeckillProductDAO;
import com.oliver.entity.vo.ResultVO;
import com.oliver.mq.producer.AmSeckillProducer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.oliver.controller.am.SeckillProduct
 *
 * @author oliver
 * @date 2020/1/9 10:34
 */
@RestController
@RequestMapping("/am/secpro")
public class SeckillProduct {

    @Resource
    private AmSeckillProductDAO seckillProductDAO;

    @GetMapping("list")
    public Object getList(@PathVariable("amid") long amId) {
        return ResultVO.success(seckillProductDAO.selectSecKillProducts(amId));
    }

    @PostMapping("/sp")
    public Object add(@RequestBody List<AmProduct> amProducts) {
        amProducts.stream().forEach(amProduct->{
            AmSeckillProduct seckillProduct = new AmSeckillProduct();
            seckillProduct.setSeckillCount(amProduct.getProductCount());
            seckillProduct.setSeckillProductPrice(amProduct.getProductPrice());
        });
        return ResultVO.success("");
    }

    @PutMapping("{id}")
    public Object update(@PathVariable("id") Long id, @RequestBody AmSeckillProduct seckillProduct) {
        seckillProduct.setId(id);
        int num = seckillProductDAO.updateByPrimaryKeySelective(seckillProduct);
        if (num != 0) {
            return ResultVO.success("更新成功");
        }
        return ResultVO.error("更新失败");
    }
}

