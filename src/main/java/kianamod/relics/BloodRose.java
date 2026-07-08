package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import kianamod.powers.HonkaiEnergy;

/**
 * 血色玫瑰 (BloodRose) — COMMON
 * 回合开始时如果生命值 < 50%，获得 1 层崩坏能。
 * 在鲜血中绽放的玫瑰，带来崩坏的力量。
 */
public class BloodRose extends CustomRelic {
    public static final String ID = "ExampleMod:BloodRose";
    private static final String IMG_PATH = "ExampleModResources/img/relics/BloodRose.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/BloodRose_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    private static final int HONKAI_AMOUNT = 1;

    public BloodRose() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && !p.isDeadOrEscaped() && p.currentHealth > 0
                && p.currentHealth <= p.maxHealth / 2) {
            this.flash();
            this.addToBot(new ApplyPowerAction(p, p,
                    new HonkaiEnergy(p, HONKAI_AMOUNT), HONKAI_AMOUNT));
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BloodRose();
    }
}
