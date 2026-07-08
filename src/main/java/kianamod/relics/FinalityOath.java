package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;

/**
 * 终焉的约定 (FinalityOath) — BOSS
 * 进入终焉之律者形态时，获得 20 点临时最大生命值并治疗 20 点生命。
 */
public class FinalityOath extends CustomRelic {
    public static final String ID = "ExampleMod:FinalityOath";
    private static final String IMG_PATH = "ExampleModResources/img/relics/FinalityOath.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/FinalityOath_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.HEAVY;
    private static final int HP_BONUS = 20;

    public FinalityOath() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (newStance != null && "ExampleMod:EndStance".equals(newStance.ID)) {
            this.flash();
            AbstractCreature p = AbstractDungeon.player;
            p.increaseMaxHp(HP_BONUS, true);
            this.addToBot(new HealAction(p, p, HP_BONUS));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new FinalityOath();
    }
}
