package sld.vinmusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sld.vinmusic.dto.BaseResponse;
import sld.vinmusic.dto.VinRequest;
import sld.vinmusic.entity.Vinlike;
import sld.vinmusic.service.impl.VinServiceDBImpl;

@RestController
@RequestMapping("/api/vin/")
public class VinController {
    @Autowired
    private VinServiceDBImpl vinService;

    @RequestMapping(value = "/doVin", method = RequestMethod.POST)
    public BaseResponse doVin(@RequestBody VinRequest req) {
        BaseResponse resp;
        try {
            resp = req.isLike() ? like(req) : unlike(req);
        } catch (Exception e) {
            e.printStackTrace();
            resp = BaseResponse.builder()
                    .ok(false)
                    .msg("参数错误")
                    .code(0)
                    .build();
        }
        return resp;
    }

    private BaseResponse like(VinRequest req) {
        var vinLike = new Vinlike();
        vinLike.setMoodId(req.getMood());
        vinLike.setAvId(req.getAccount());

        if(vinService.isLiked(vinLike))
            return BaseResponse.builder()
                    .ok(false)
                    .msg("不能给同一条音文点赞")
                    .code(0)
                    .build();

        vinService.addVin(vinLike);
        return BaseResponse.builder()
                .ok(true)
                .msg("点赞成功")
                .code(1)
                .build();
    }

    private BaseResponse unlike(VinRequest req) {
        var vinLike = new Vinlike();

        vinLike.setMoodId(req.getMood());
        vinLike.setAvId(req.getAccount());

        if(!vinService.isLiked(vinLike))
            return BaseResponse.builder()
                    .ok(false)
                    .msg("不能重复取消操作！")
                    .code(0)
                    .build();

        vinLike = vinService.getHistoryVin(vinLike);

        vinService.withdrawVin(vinLike);

        return BaseResponse.builder()
                .ok(true)
                .msg("取消成功")
                .code(1)
                .build();
    }
}
