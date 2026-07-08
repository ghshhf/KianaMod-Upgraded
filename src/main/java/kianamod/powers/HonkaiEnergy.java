package kianamod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 崩坏能 (HonkaiEnergy)
 * 琪亚娜的核心资源系统：3/7/11 三档阈值
 * 战斗开始时从 0 开始累积，被火焰/虚无/终焉卡牌消耗或生成
 */
public class HonkaiEnergy extends AbstractPower {
    public static final String POWER_ID = "ExampleMod:HonkaiEnergy";
    public static final int TIER_1 = 3;
    public static final int TIER_2 = 7;
    public static final int TIER_3 = 11;

    public HonkaiEnergy(AbstractCreature owner, int amount) {
        this.name = "崩坏能";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "崩坏能层数: " + this.amount
                + "。到达 " + TIER_1 + "/" + TIER_2 + "/" + TIER_3
                + " 层时触发对应效果。";
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

    /**
     * 获取当前档位 (0=未达阈值, 1=TIER_1, 2=TIER_2, 3=TIER_3)
     */
    public int getTier() {
        if (this.amount >= TIER_3) return 3;
        if (this.amount >= TIER_2) return 2;
        if (this.amount >= TIER_1) return 1;
        return 0;
    }
}
