package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;

import kianamod.powers.HonkaiEnergy;

/**
 * 真理之钥 (TruthKey) — UNCOMMON
 * 进入非中立形态时，获得 1 点崩坏能。
 * 真理之钥解锁崩坏能的真正力量。
 */
public class TruthKey extends CustomRelic {
    public static final String ID = "ExampleMod:TruthKey";
    private static final String IMG_PATH = "ExampleModResources/img/relics/TruthKey.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/TruthKey_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public TruthKey() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (newStance != null && !(newStance instanceof NeutralStance)) {
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new HonkaiEnergy(AbstractDungeon.player, 1), 1));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TruthKey();
    }
}
