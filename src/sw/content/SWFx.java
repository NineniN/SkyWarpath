package sw.content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mat;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import arc.util.Log;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import sw.expand.effects.AnimationEffect;

public class SWFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

    cirrhinusHit = new MultiEffect(
            new WaveEffect(){{
                lifetime = 22;
                sizeFrom = 0;
                sizeTo = 32;
                strokeFrom = 2;
                strokeTo = 0;
                colorFrom = Color.valueOf("ffffff");
                colorTo = Color.valueOf("FFD37F");
            }}
    ),
    engulfCharge1 = new MultiEffect(
            new ParticleEffect(){{
                line = true;
                interp = Interp.pow5In;
                sizeInterp = Interp.pow5Out;

                lifetime = 22;
                particles = 6;
                baseLength = -22;
                length = 24;
                lenFrom = 1;
                lenTo = 3;
                strokeFrom = 0;
                strokeTo = 1.6f;
                colorFrom = Color.valueOf("A4A5F5");
                colorTo = Color.valueOf("A4A5F5");
            }},
            new ParticleEffect(){{
                //interp = Interp.pow5In;
                //sizeInterp = Interp.pow5Out;

                lifetime = 22;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 0;
                sizeTo = 3;
                colorFrom = Color.valueOf("A4A5F5");
                colorTo = Color.valueOf("A4A5F5");
            }}
    ),
    engulfShoot1 = new MultiEffect(
            new ParticleEffect(){{
                line = true;

                lifetime = 30;
                particles = 3;
                cone = 24;
                baseLength = 0;
                length = 24;
                strokeFrom = 2.2f;
                lenFrom = 6;
                lenTo = 4;
                strokeTo = 0;
                colorFrom = Color.valueOf("A4A5F5");
                colorTo = Color.valueOf("A4A5F5");
            }},
            new WaveEffect(){{
                interp = Interp.pow10Out;

                lifetime = 20;
                sizeFrom = 0;
                sizeTo = 24;
                strokeFrom = 3;
                strokeTo = 0;
                colorFrom = Color.white;
                colorTo = Color.valueOf("A4A5F5");
            }}
    ),
    engulfHit1 = new MultiEffect(
            new ParticleEffect(){{
                lifetime = 30;
                particles = 3;
                baseLength = 0;
                length = 24;
                sizeFrom = 3;
                sizeTo = 0;
                colorFrom = Color.valueOf("A4A5F5");
                colorTo = Color.valueOf("A4A5F5");
            }},
            new WaveEffect(){{
                interp = Interp.pow10Out;

                lifetime = 30;
                sizeFrom = 0;
                sizeTo = 25;
                strokeFrom = 3;
                strokeTo = 0;
                colorFrom = Color.white;
                colorTo = Color.valueOf("A4A5F5");
            }}
    ),
    engulfCharge2 = new MultiEffect(
            new ParticleEffect(){{
                line = true;
                interp = Interp.pow5In;
                sizeInterp = Interp.pow5Out;

                lifetime = 22;
                particles = 6;
                baseLength = -22;
                length = 24;
                lenFrom = 1;
                lenTo = 3;
                strokeFrom = 0;
                strokeTo = 1.6f;
                colorFrom = Color.white;
                colorTo = Color.white;
            }},
            new ParticleEffect(){{
                //interp = Interp.pow5In;
                //sizeInterp = Interp.pow5Out;

                lifetime = 22;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 0;
                sizeTo = 3;
                colorFrom = Color.white;
                colorTo = Color.white;
            }}
    ),
    engulfShoot2 = new MultiEffect(
            new ParticleEffect(){{
                line = true;

                lifetime = 30;
                particles = 3;
                cone = 24;
                baseLength = 0;
                length = 24;
                strokeFrom = 2.2f;
                lenFrom = 6;
                lenTo = 4;
                strokeTo = 0;
                colorFrom = Color.white;
                colorTo = Color.white;
            }},
            new WaveEffect(){{
                interp = Interp.pow10Out;

                lifetime = 20;
                sizeFrom = 0;
                sizeTo = 24;
                strokeFrom = 3;
                strokeTo = 0;
                colorFrom = Color.white;
                colorTo = Color.white;
            }}
    ),
    engulfHit2 = new MultiEffect(
            new ParticleEffect(){{
                lifetime = 30;
                particles = 3;
                baseLength = 0;
                length = 44;
                sizeFrom = 5;
                sizeTo = 0;
                colorFrom = Color.white;
                colorTo = Color.white;
            }},
            new WaveEffect(){{
                interp = Interp.pow10Out;

                lifetime = 30;
                sizeFrom = 0;
                sizeTo = 50;
                strokeFrom = 3;
                strokeTo = 0;
                colorFrom = Color.white;
                colorTo = Color.white;
            }}
    ),
    spineDespawn = new MultiEffect(
            new WaveEffect(){{
                interp = Interp.pow10Out;

                lifetime = 30;
                sizeFrom = 0;
                sizeTo = 12;
                strokeFrom = 2;
                strokeTo = 0;
                colorFrom = Color.valueOf("989AA4");
                colorTo = Color.valueOf("989AA4");
            }}
    ),
    fan = new AnimationEffect(){{
        sprite = "fan";
        regions = 4;

        lifetime = 9;
        sizeFrom = 24;
        sizeTo = 24;
    }},
    starFallSpawnSmall = new MultiEffect(
            //方形扩散波
            new Effect(150,e -> {
                float angle = (float)Math.sin(Math.PI * 2 * e.fin());

                Lines.stroke(3 + angle,Color.valueOf("9e9e9e"));
                Lines.beginLine();
                for(int i = 0;i < 6;i++){
                    Vec2 vec = Vec2.X.trns(i * 90 + e.fin() * 60,e.fout() * (8 + 2 * angle)).add(e.x,e.y);
                    Lines.linePoint(vec);
                }
                Lines.endLine();
            }),
            new Effect(100,e -> {
                float length1 = 80 + 160 * e.fout(Interp.pow5Out);
                float length2 = 24 * e.fout(Interp.circleOut);
                float width = 4 + 4 * e.fout(Interp.circleIn);

                Draw.color(Color.valueOf("9e9e9e"));
                for(int i = 0;i < 4;i++){
                    Vec2 vec = Vec2.X.trns(i * 90,32).add(e.x,e.y);
                    Drawf.tri(vec.x,vec.y,width,length1,i * 90);
                    Drawf.tri(vec.x,vec.y,width,length2,i * 90 + 180);
                }
            })
    ),
    starFallExplosionSmall = new MultiEffect(
            new Effect(240,e -> {
                float length = 160 * e.fout(Interp.pow10In);
                float width = 12 * e.fout(Interp.pow5In);

                Draw.color(Color.valueOf("9e9e9e"));
                for(int i = 0;i < 4;i++){
                    Drawf.tri(e.x,e.y,width,length,i * 90 + 45);
                }
            }),
            new WaveEffect(){{
                interp = Interp.pow10Out;

                lifetime = 40;
                sizeFrom = 5;
                sizeTo = 100;
                strokeFrom = 8;
                strokeTo = 0;
            }}
    ),
    starFallSpawn = new MultiEffect(
            new Effect(150,e -> {
                float angle = (float)Math.sin(Math.PI * 2 * e.fin());

                Lines.stroke((12 + 4 * angle) * e.fin(Interp.pow10Out),Color.valueOf("9e9e9e"));
                Lines.beginLine();
                for(int i = 0;i < 6;i++){
                    Vec2 vec = Vec2.X.trns(i * 90 + e.fin() * 160,(120 + 8 * angle) * e.fout()).add(e.x,e.y);
                    Lines.linePoint(vec);
                }
                Lines.endLine();

                Lines.stroke((8 + 2 * angle) * e.fin(Interp.pow10Out),Color.valueOf("9e9e9e40"));
                Lines.beginLine();
                for(int i = 0;i < 6;i++){
                    Vec2 vec = Vec2.X.trns(i * 90 + e.fin() * 80,(360 + 12 * angle) * e.fout()).add(e.x,e.y);
                    Lines.linePoint(vec);
                }
                Lines.endLine();

                Lines.stroke((4 + angle) * e.fin(Interp.pow10Out),Color.valueOf("9e9e9e80"));
                Lines.beginLine();
                for(int i = 0;i < 6;i++){
                    Vec2 vec = Vec2.X.trns(i * 90 + e.fin() * 40,(1080 + 12 * angle) * e.fout()).add(e.x,e.y);
                    Lines.linePoint(vec);
                }
                Lines.endLine();
            })
    ),
    starFallExplosion = new MultiEffect(
            //十字架
            new Effect(120,e -> {
                Draw.color(Color.valueOf("9e9e9e"));

                float length1 = 280 * e.fout(Interp.circleIn);
                float width1 = 28 * e.fout(Interp.circleIn);
                float length2 = 173 * e.fout(Interp.circleIn);
                float width2 = 17 * e.fout(Interp.circleIn);
                Drawf.tri(e.x,e.y,width1,length1,0);
                Drawf.tri(e.x,e.y,width1,length1,180);
                Drawf.tri(e.x,e.y,width2,length2,90);
                Drawf.tri(e.x,e.y,width2,length2,-90);
            }),
            new Effect(80,e -> {
                float baseLength = 60;
                float length = 240;
                float maxHeight = 1000;
                float width = 30;

                Draw.color(Pal.surge);
                if(e.time < 30){
                    float progress = e.time / 30;

                    for(int i = 0;i < 2;i++){
                        Vec2 vec = Vec2.ZERO.trns(90 + i * 180,baseLength).add(e.x,e.y);

                        Drawf.tri(vec.x,vec.y,maxHeight * Interp.pow10Out.apply(progress),width,vec.angle());
                    }
                } else if(e.time > 50){
                    float progress = (e.time - 50) / 30;
                    float easeIn = progress * progress;
                    float easeOut = 1 - easeIn;

                    for(int i = 0;i < 2;i++){
                        Vec2 vec = Vec2.ZERO.trns(90 + i * 180,baseLength + length * easeIn).add(e.x,e.y);

                        Drawf.tri(vec.x,vec.y,maxHeight * easeOut,width * Interp.circleOut.apply(progress),vec.angle());
                    }
                } else {
                    for(int i = 0;i < 2;i++){
                        Vec2 vec = Vec2.ZERO.trns(90 + i * 180,baseLength).add(e.x,e.y);

                        Drawf.tri(vec.x,vec.y,maxHeight,width,vec.angle());
                    }
                }

            })
    ),
    gungnirError = new MultiEffect(
            new Effect(160,e -> {
                float x = Mathf.log(4,e.fin(Interp.pow10In) * 32 + 0.125f) / e.time * 16;
                Vec2 vec = Vec2.ZERO.trns(e.rotation + 90,x);
                Vec2 vec1 = Vec2.X.set(vec).add(e.x,e.y);
                Vec2 vec2 = Vec2.X.set(vec).scl(-1).add(e.x,e.y);

                Draw.color(Pal.surge);
                Fill.circle(vec1.x,vec1.y,3 * e.fout());
                Fill.circle(vec2.x,vec2.y,3 * e.fout());
            })
    ),
    gungnirHit = new MultiEffect(
            new ParticleEffect(){{
                line = true;

                cone = 36;
                lifetime = 20;
                particles = 16;
                baseLength = -32;
                length = -120;
                strokeFrom = 3;
                strokeTo = 1;
                lenFrom = 8;
                lenTo = 3;
                colorFrom = Pal.surge;
                colorTo = Pal.surge;
            }},
            new ParticleEffect(){{
                line = true;

                cone = 30;
                lifetime = 14;
                particles = 8;
                baseLength = -32;
                length = -200;
                strokeFrom = 3;
                strokeTo = 1;
                lenFrom = 8;
                lenTo = 3;
                colorFrom = Pal.surge;
                colorTo = Pal.surge;
            }},
            new Effect(26,e -> {
                Draw.color(Pal.surge);
                Drawf.tri(e.x,e.y,6 * e.fout(Interp.pow3In) + 2,8 + 16 * e.fin(Interp.circleOut),e.rotation);
                Drawf.tri(e.x,e.y,6 * e.fout(Interp.pow3In) + 2,24 + 126 * e.fin(Interp.circleOut),e.rotation + 180);
            }),
            new Effect(20,e -> {
                Draw.color(Pal.surge);
                rand.setSeed(e.id);
                float angle1 = rand.random(360);
                float width1 = rand.random(6,8);
                float len1 = rand.random(30,50);
                rand.setSeed(e.id % 799);
                float angle2 = angle1 + rand.random(45,90);
                float width2 = rand.random(6,8);
                float len2 = rand.random(30,40);
                Drawf.tri(e.x,e.y,width1 * e.fout(Interp.pow10Out) + 2,len1,angle1);
                Drawf.tri(e.x,e.y,width1 * e.fout(Interp.pow10Out) + 2,len1,angle1 + 180);
                Drawf.tri(e.x,e.y,width2 * e.fout(Interp.pow10Out) + 2,len2,angle2);
                Drawf.tri(e.x,e.y,width2 * e.fout(Interp.pow10Out) + 2,len2,angle2 + 180);
            }),
            new Effect(32,e -> {
                Draw.color(Pal.surge);

                rand.setSeed(e.id);
                int n = rand.random(3,6);
                for(int i = 0;i < n;i++){
                    rand.setSeed(e.id + i * rand.random(325,799));
                    Vec2 vec1 = Vec2.ZERO.trns(e.rotation + 180,rand.random(24,80)).scl(i);
                    Vec2 vec2 = Vec2.ZERO.trns(e.rotation + 180 + 27,rand.random(32)).add(vec1).add(e.x,e.y);
                    Vec2 vec3 = Vec2.ZERO.trns(e.rotation + 180 + 27,rand.random(32));

                    float width = rand.random(4,6);
                    float length = rand.random(8,16);
                    Drawf.tri(vec2.x,vec2.y,width * e.fout(),length,vec3.angle() + 27);
                    Drawf.tri(vec2.x,vec2.y,width * e.fout(),length,vec3.angle() + 180 + 27);
                }
            }),
            new Effect(32,e -> {
                Draw.color(Pal.surge);

                rand.setSeed(e.id);
                int n = rand.random(3,6);
                for(int i = 0;i < n;i++){
                    rand.setSeed(e.id + i * rand.random(325,799));
                    Vec2 vec1 = Vec2.ZERO.trns(e.rotation + 180,rand.random(24,80)).scl(i);
                    Vec2 vec2 = Vec2.ZERO.trns(e.rotation + 180 - 27,rand.random(32)).add(vec1).add(e.x,e.y);
                    Vec2 vec3 = Vec2.ZERO.trns(e.rotation + 180 - 27,rand.random(32));

                    float width = rand.random(4,6);
                    float length = rand.random(8,16);
                    Drawf.tri(vec2.x,vec2.y,width * e.fout(),length,vec3.angle() - 27);
                    Drawf.tri(vec2.x,vec2.y,width * e.fout(),length,vec3.angle() + 180 - 27);
                }
            }),
            new Effect(32,e -> {
                rand.setSeed(e.id);
                int n = rand.random(28,40);

                Draw.color(Pal.surge);
                for(int i = 0;i < n;i++){
                    rand.setSeed(e.id + i);
                    float y = 24 * Mathf.sin(2 * (float)Math.PI * i / n) + (2 - 4 * Mathf.sin(rand.random((float) Math.PI * 2))) * e.fin(Interp.pow10Out);
                    float x = 12 * Mathf.cos(2 * (float)Math.PI * i / n) + (2 - 4 * Mathf.cos(rand.random((float) Math.PI * 2))) * e.fin(Interp.pow10Out);
                    float radius = rand.random(3,5);

                    Vec2 vec = Vec2.ZERO.trns(e.rotation + 180,60);
                    Vec2 pos = new Vec2(x,y).rotate(e.rotation).add(vec).add(e.x,e.y);

                    Fill.circle(pos.x,pos.y,radius * e.fout());
                }
            })
    ),
    gungnirShoot = new MultiEffect(
            new Effect(50,e -> {
                rand.setSeed(e.id);
                int n = rand.random(28,40);

                Draw.color(Pal.surge);
                for(int i = 0;i < n;i++){
                    rand.setSeed(e.id + i);
                    float y = 24 * Mathf.sin(2 * (float)Math.PI * i / n) + (2 - 4 * Mathf.sin(rand.random((float) Math.PI * 2))) * e.fin(Interp.pow10Out);
                    float x = 12 * Mathf.cos(2 * (float)Math.PI * i / n) + (2 - 4 * Mathf.cos(rand.random((float) Math.PI * 2))) * e.fin(Interp.pow10Out);
                    float radius = rand.random(3,5);

                    Vec2 vec = Vec2.ZERO.trns(e.rotation,60);
                    Vec2 pos = new Vec2(x,y).rotate(e.rotation).add(vec).add(e.x,e.y);

                    Fill.circle(pos.x,pos.y,radius * e.fout());
                }
            }),
            new Effect(50,e -> {
                rand.setSeed(e.id);
                int n = rand.random(28,40);

                Draw.color(Pal.surge);
                for(int i = 0;i < n;i++){
                    rand.setSeed(e.id + i);
                    float y = 48 * Mathf.sin(2 * (float)Math.PI * i / n) + (2 - 4 * Mathf.sin(rand.random((float) Math.PI * 2))) * e.fin(Interp.pow10Out);
                    float x = 16 * Mathf.cos(2 * (float)Math.PI * i / n) + (2 - 4 * Mathf.cos(rand.random((float) Math.PI * 2))) * e.fin(Interp.pow10Out);
                    float radius = rand.random(3,5);

                    Vec2 vec = Vec2.ZERO.trns(e.rotation,30);
                    Vec2 pos = new Vec2(x,y).rotate(e.rotation).add(vec).add(e.x,e.y);

                    Fill.circle(pos.x,pos.y,radius * e.fout());
                }
            })
    ),
    gungnirTrail = new MultiEffect(
            new Effect(60,e -> {
                rand.setSeed(e.id);
                Vec2 ang = Vec2.X.trns(e.rotation + rand.random(24) - 12,24 * e.fin(Interp.pow10Out) * rand.random(0.5f,1.5f));
                Vec2 pos = new Vec2(ang.x,ang.y).add(e.x,e.y);
                float len = rand.random(5,12);
                float wid = rand.random(2,3);

                Draw.color(Color.valueOf("D4B46A"));
                Drawf.tri(pos.x,pos.y,wid * e.fout(Interp.circleOut),len,ang.angle());
                Drawf.tri(pos.x,pos.y,wid * e.fout(Interp.circleOut),len,ang.angle() + 180);
            })
    );


    /*
    public static void loadTexture(){
        Fi textureDir = Core.files.internal("sprites/effects/");

        for(int i = 1; i <= 3; i++){
            String name = "fan" + i;
            Fi file = textureDir.child("fan/" + name + ".png");
            if(file.exists()){
                addTexture(name, file);
            }
        }
    }


    private static void addTexture(String name, Fi file){
        if(file.exists()){
            // 创建纹理并添加到图集
            Texture texture = new Texture(file);
            Core.atlas.addRegion(name, texture, 0, 0, texture.width, texture.height);
            Log.info("已添加纹理: @", name);
        }else{
            Log.err("纹理文件不存在: @", file.path());
        }
    }
    */

}
