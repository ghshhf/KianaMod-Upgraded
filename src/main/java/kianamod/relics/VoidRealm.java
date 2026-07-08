package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;

/**
 * 空无之境 (VoidRealm) — UNCOMMON
 * 进入空之律者形态时，额外抽 2 张牌。
 * 虚数之树的彼岸，空之律者的王座。
 */
public class VoidRealm extends CustomRelic {
    public static final String ID = "ExampleMod:VoidRealm";
    private static final String IMG_PATH = "ExampleModResources/img/relics/VoidRealm.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/VoidRealm_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    private static final int DRAW_AMOUNT = 2;

    public VoidRealm() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (newStance != null && "ExampleMod:SpaceStance".equals(newStance.ID)) {
            this.flash();
            this.addToBot(new DrawCardAction(AbstractDungeon.player, DRAW_AMOUNT));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new VoidRealm();
    }
}
