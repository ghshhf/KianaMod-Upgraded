package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import kianamod.powers.BurnPower;

/**
 * 天火圣痕 (HeavenFireMark) — COMMON
 * 战斗开始时，对所有敌人施加 2 层烧伤（BurnPower）。
 * 以火焰惩戒世间一切之恶。
 */
public class HeavenFireMark extends CustomRelic {
    public static final String ID = "ExampleMod:HeavenFireMark";
    private static final String IMG_PATH = "ExampleModResources/img/relics/HeavenFireMark.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/HeavenFireMark_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    private static final int BURN_AMOUNT = 2;

    public HeavenFireMark() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        AbstractCreature p = AbstractDungeon.player;
        for (AbstractCreature m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(m, p, new BurnPower(p, m, BURN_AMOUNT), BURN_AMOUNT));
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HeavenFireMark();
    }
}
