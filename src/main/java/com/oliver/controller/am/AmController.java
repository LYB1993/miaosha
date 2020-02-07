package com.oliver.controller.am;

import com.oliver.entity.am.AmSeckill;
import com.oliver.entity.am.AmSeckillDAO;
import com.oliver.entity.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * com.oliver.controller.am.AmController
 *
 * @author oliver
 * @date 2020/1/3 13:23
 */
@RestController
@RequestMapping("/am")
public class AmController {

    @Resource
    private AmSeckillDAO baseDao;

    @GetMapping("list")
    public Object amList() {
        return ResultVO.success(baseDao.selectAll());
    }

    @GetMapping("{id}")
    public Object getAmById(@PathVariable("id") Long id){
        return ResultVO.success( baseDao.selectByPrimaryKey(id));
    }

    @PostMapping("/add")
    public Object addAm(@RequestBody AmSeckill amSeckill) {
        int insert = baseDao.insertSelective(amSeckill);
        if (insert > 0) {
            return ResultVO.success(amSeckill, "添加成功");
        }
        return ResultVO.error("添加失败");

    }

    @PutMapping("{id}")
    public Object updateAm(@PathVariable("id") Long id, @RequestBody AmSeckill seckill) {
        seckill.setId(id);
        int num = baseDao.updateByPrimaryKeySelective(seckill);
        if (num != 0) {
            return ResultVO.success("更新成功");
        }
        return ResultVO.error("更新失败");
    }

    @DeleteMapping("{id}")
    public Object deleteAm(@PathVariable("id") Long amid) {
        int num = baseDao.deleteByPrimaryKey(amid);
        if (num > 0) {
            return ResultVO.success("删除成功");
        }
        return ResultVO.error("删除失败");
    }
}
