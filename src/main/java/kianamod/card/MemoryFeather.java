package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import kianamod.characters.MyCharacter;
import kianamod.powers.HonkaiEnergy;

/**
 * 以此仙忆化羽 (MemoryFeather)
 * 费用 1 | UNCOMMON | 能力 | 自身
 * 每回合开始时，获得 1(2) 层崩坏能。
 * 原作概念：羽化增益被动，积累祝福。
 */
public class MemoryFeather extends CustomCard {
    public static final String ID = "ExampleMod:MemoryFeather";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/MemoryFeather.png";

    private static final int COST = 1;
    private static final int BASE_AMT = 1;
    private static final int UPG_AMT = 2;

    public MemoryFeather() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MemoryFeatherPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_AMT - BASE_AMT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MemoryFeather();
    }

    /**
     * 以此仙忆化羽的被动能力：每回合开始时获得崩坏能。
     */
    public static class MemoryFeatherPower extends AbstractPower {
        public static final String POWER_ID = "ExampleMod:MemoryFeatherPower";

        public MemoryFeatherPower(AbstractCreature owner, int amount) {
            this.name = "仙忆化羽";
            this.ID = POWER_ID;
            this.owner = owner;
            this.amount = amount;
            this.type = PowerType.BUFF;
            this.isTurnBased = false;
            this.updateDescription();
        }

        @Override
        public void updateDescription() {
            this.description = "每回合开始时，获得 #y" + this.amount + " 层 #y崩坏能 。";
        }

        @Override
        public void atStartOfTurn() {
            if (this.owner == null || this.owner.isDeadOrEscaped()) {
                return;
            }
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner, this.owner,
                            new HonkaiEnergy(this.owner, this.amount), this.amount));
        }
    }
}
