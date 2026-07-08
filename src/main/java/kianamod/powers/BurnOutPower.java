package kianamod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import kianamod.powers.BurnPower;
import kianamod.powers.HonkaiEnergy;

/**
 * 燃尽 (BurnOutPower)
 * 回合开始时，对自己施加火焰元素伤害，并获得崩坏能。
 * 强度做足：每层每回合造成等量火焰元素伤害，并稳定补充 2 点崩坏能。
 */
public class BurnOutPower extends AbstractPower {
    public static final String POWER_ID = "ExampleMod:BurnOutPower";
    private static final String NAME = "燃尽";
    private static final int HONKAI_PER_TURN = 2;

    public BurnOutPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = "回合开始时，对自己施加 " + this.amount + " 点火焰元素伤害，并获得 "
                + HONKAI_PER_TURN + " 点崩坏能。";
    }

    @Override
    public void atStartOfTurn() {
        if (this.owner == null || this.owner.isDeadOrEscaped()) {
            return;
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner,
                new BurnPower(this.owner, this.owner, this.amount), this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner,
                new HonkaiEnergy(this.owner, HONKAI_PER_TURN), HONKAI_PER_TURN));
    }
}
