package kianamod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 灼烧 (BurnPower) - 每回合造成火焰元素伤害
 * 被 HeavenFireMark 遗物、PrairieFire 卡牌等引用
 */
public class BurnPower extends AbstractPower {
    public static final String POWER_ID = "ExampleMod:BurnPower";
    
    public BurnPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = "灼烧";
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.updateDescription();
    }
    
    @Override
    public void updateDescription() {
        this.description = "每回合结束时受到 " + this.amount + " 点火焰元素伤害。";
    }
    
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        // 火焰元素伤害：无类型伤害，穿甲
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(
            new com.megacrit.cardcrawl.actions.common.DamageAction(
                this.owner,
                new com.megacrit.cardcrawl.cards.DamageInfo(
                    this.owner, this.amount, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.HP_LOSS
                ),
                com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.FIRE
            )
        );
    }
}
