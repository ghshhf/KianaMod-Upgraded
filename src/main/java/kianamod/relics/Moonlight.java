package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import kianamod.powers.HonkaiEnergy;

/**
 * 月煌 (Moonlight) — COMMON
 * 每场战斗开始时获得 1 点崩坏能。
 * 月光指引着战斗的方向。
 */
public class Moonlight extends CustomRelic {
    public static final String ID = "ExampleMod:Moonlight";
    private static final String IMG_PATH = "ExampleModResources/img/relics/Moonlight.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/Moonlight_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    private static final int HONKAI_AMOUNT = 1;

    public Moonlight() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new HonkaiEnergy(AbstractDungeon.player, HONKAI_AMOUNT), HONKAI_AMOUNT));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Moonlight();
    }
}
