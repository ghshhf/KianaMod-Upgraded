package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import kianamod.characters.MyCharacter;
import kianamod.stances.EndStance;
import kianamod.util.FlameCardUtils;

/**
 * 终末姿态 (FinalStance)
 * 费用 2 | RARE | 能力 | 自身
 * 进入终焉之律者形态。每回合开始时获得 1(2) 崩坏能并对全体敌人造成 3(5) 火焰伤害。
 * 原作概念：终末姿态终极形态。
 */
public class FinalStance extends CustomCard {
    public static final String ID = "ExampleMod:FinalStance";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/FinalStance.png";

    private static final int COST = 2;
    private static final int BASE_HONKAI = 1;
    private static final int UPG_HONKAI = 2;
    private static final int BASE_DMG = 3;
    private static final int UPG_DMG = 5;

    public FinalStance() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_HONKAI;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = BASE_DMG;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 进入终焉之律者形态
        addToBot(new ChangeStanceAction(new EndStance()));
        // 施加终末姿态能力
        addToBot(new ApplyPowerAction(p, p, new FinalStancePower(p, this.magicNumber, this.upgraded ? UPG_DMG : BASE_DMG), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_HONKAI - BASE_HONKAI);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinalStance();
    }

    /**
     * 终末姿态的被动能力：每回合开始时获得崩坏能并对全体敌人造成火焰伤害。
     */
    public static class FinalStancePower extends AbstractPower {
        public static final String POWER_ID = "ExampleMod:FinalStancePower";
        private final int damage;

        public FinalStancePower(AbstractCreature owner, int honkaiAmt, int damage) {
            this.name = "终末姿态";
            this.ID = POWER_ID;
            this.owner = owner;
            this.amount = honkaiAmt;
            this.damage = damage;
            this.type = PowerType.BUFF;
            this.isTurnBased = false;
            this.updateDescription();
        }

        @Override
        public void updateDescription() {
            this.description = "每回合开始时，获得 #y" + this.amount + " 点 #y崩坏能 并对全体敌人造成 #y"
                    + this.damage + " 点火焰伤害。";
        }

        @Override
        public void atStartOfTurn() {
            if (this.owner == null || this.owner.isDeadOrEscaped()) {
                return;
            }
            flash();
            // 获得崩坏能
            FlameCardUtils.addHonkai(this.owner, this.amount);
            // 对全体敌人造成火焰伤害（owner 必然是玩家）
            if (this.owner instanceof AbstractPlayer) {
                AbstractPlayer player = (AbstractPlayer) this.owner;
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAllEnemiesAction(player, this.damage,
                                DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }
}
