package sw.expand.effects;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.util.Log;
import arc.util.Nullable;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import static arc.Core.files;

public class AnimationEffect extends Effect {
    private static final Rand rand = new Rand();

    public Interp interp = Interp.linear;
    public Interp sizeInterp = Interp.linear;

    public Color colorFrom = Color.white, colorTo = Color.white;
    public Color lightColor;
    public float lightScl = 2, lightOpacity = 0.6f;
    public float sizeChangeStart = 0, sizeFrom = 40, sizeTo = 40;

    // 新增参数
    public float offsetX, offsetY;
    public boolean useRotation = true;
    public float baseRotation = 0f;

    public String sprite = "circle";
    public int regions = 1;
    private @Nullable TextureRegion[] texs;

    @Override
    public void init(){
        // 设置裁剪范围，确保特效在视野内时会被渲染
        clip = Math.max(clip, Math.max(sizeFrom, sizeTo));
        sizeChangeStart = Mathf.clamp(sizeChangeStart, 0f, lifetime);

        texs = new TextureRegion[regions];
        for(int i = 1;i <= regions;i++){
            texs[i - 1] = Core.atlas.find("sky-warpath-" + sprite + i);
            Log.info(texs[i - 1].found());
        }
    }

    @Override
    public void render(EffectContainer e){
        float fin = e.fin(interp);
        float rawfin = e.fin();
        float rad = sizeInterp.apply(sizeFrom, sizeTo, Mathf.curve(rawfin, sizeChangeStart / lifetime, 1f)) * 2;


        // 安全的索引计算
        int index;
        if(regions == 1){
            index = 0;
        } else {
            float dst = 1f / regions;
            index = Math.min((int)(fin / dst), regions - 1);
        }

        Draw.color(colorFrom, colorTo, fin);
        Color lightColor = this.lightColor == null ? Draw.getColor() : this.lightColor;

        // 绘制纹理
        if(texs[index] != null && texs[index].found()){
            rand.setSeed(e.id);
            Draw.rect(texs[index], e.x, e.y, rad, rad / texs[index].ratio(), (float)(rand.random(360)));
            Drawf.light(e.x, e.y, rad * lightScl, lightColor, lightOpacity * Draw.getColorAlpha());
        }

        Draw.reset();
    }
}