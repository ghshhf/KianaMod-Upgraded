package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import kianamod.powers.HonkaiEnergy;

/**
 * 伊甸之星 (EdenStar) — UNCOMMON
 * 每回合开始时获得 1 层崩坏能。
 * 来自乐园的星辰，持续供给崩坏之力。
 */
public class EdenStar extends CustomRelic {
    public static final String ID = "ExampleMod:EdenStar";
    private static final String IMG_PATH = "ExampleModResources/img/relics/EdenStar.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/EdenStar_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    private static final int HONKAI_AMOUNT = 1;

    public EdenStar() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        this.flash();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new HonkaiEnergy(AbstractDungeon.player, HONKAI_AMOUNT), HONKAI_AMOUNT));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EdenStar();
    }
}
