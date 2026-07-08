package kianamod.events;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import kianamod.powers.HonkaiEnergy;

/**
 * 逆熵合作 - 装甲强化
 * 本场战斗中，每次获得崩坏能时额外 +1。
 * 当玩家的 HonkaiEnergy 增加时触发额外增益。
 * 战斗结束时自动移除（isTurnBased=false，无回合概念）。
 */
public class AlliancePower extends AbstractPower {
    public static final String POWER_ID = "ExampleMod:AlliancePower";
    private static final String NAME = "逆熵合作·装甲强化";

    public AlliancePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 1;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.updateDescription();
        this.loadRegion("heartDefend");
    }

    @Override
    public void updateDescription() {
        this.description = "每次获得崩坏能时，额外获得 " + this.amount + " 点崩坏能。";
    }

    /**
     * 当任何Power被添加时调用。检测玩家是否正在获得崩坏能，
     * 若是则额外赠送1点崩坏能。
     */
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power == null || target != this.owner) {
            return;
        }
        // 检测崩坏能增加（amount > 0 表示增加，不是减少）
        if (HonkaiEnergy.POWER_ID.equals(power.ID) && power.amount > 0) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(
                            this.owner, this.owner,
                            new HonkaiEnergy(this.owner, 1), 1));
        }
    }
}
