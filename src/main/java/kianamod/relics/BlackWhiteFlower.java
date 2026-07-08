package kianamod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import kianamod.powers.HonkaiEnergy;

/**
 * 黑渊白花 (BlackWhiteFlower) — BOSS
 * 受到致命伤害时，消耗全部崩坏能，免疫此次死亡并回复 1 点生命值。每场战斗一次。
 * 创生与毁灭的双刃。
 */
public class BlackWhiteFlower extends CustomRelic {
    public static final String ID = "ExampleMod:BlackWhiteFlower";
    private static final String IMG_PATH = "ExampleModResources/img/relics/BlackWhiteFlower.png";
    private static final String OUTLINE_PATH = "ExampleModResources/img/relics/BlackWhiteFlower_Outline.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.HEAVY;

    private boolean usedThisCombat = false;

    public BlackWhiteFlower() {
        super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int onLoseHpLast(int damageAmount) {
        if (usedThisCombat) {
            return damageAmount;
        }

        AbstractPlayer p = AbstractDungeon.player;
        if (p == null || p.isDead || p.isDeadOrEscaped()) {
            return damageAmount;
        }

        // Check if this damage would be lethal
        if (p.currentHealth - damageAmount <= 0) {
            // Check if player has HonkaiEnergy
            if (p.hasPower(HonkaiEnergy.POWER_ID)) {
                int honkaiAmount = p.getPower(HonkaiEnergy.POWER_ID).amount;
                if (honkaiAmount > 0) {
                    this.flash();
                    // Consume all HonkaiEnergy
                    this.addToTop(new ReducePowerAction(p, p, HonkaiEnergy.POWER_ID, honkaiAmount));
                    // Survive with 1 HP
                    usedThisCombat = true;
                    this.stopPulse();
                    return p.currentHealth - 1;
                }
            }
        }
        return damageAmount;
    }

    @Override
    public void atBattleStart() {
        this.usedThisCombat = false;
        this.beginLongPulse();
    }

    @Override
    public void onEquip() {
        super.onEquip();
        this.beginLongPulse();
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BlackWhiteFlower();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BlackWhiteFlower;
    }
}
