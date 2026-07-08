package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.AcceleratePower;
import kianamod.util.FlameCardUtils;

/**
 * 量子隧道 (QuantumTunnel)
 * 获得 4(5) 崩坏能。获得 1(2) 层加速。消耗。
 */
public class QuantumTunnel extends CustomCard {
    public static final String ID = "ExampleMod:QuantumTunnel";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/QuantumTunnel.png";

    private static final int COST = 0;
    private static final int BASE_HONKAI = 4;
    private static final int UPG_HONKAI = 5;
    private static final int BASE_ACCEL = 1;
    private static final int UPG_ACCEL = 2;

    public QuantumTunnel() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_HONKAI;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        FlameCardUtils.addHonkai(p, this.magicNumber);
        int accelAmt = this.upgraded ? UPG_ACCEL : BASE_ACCEL;
        addToBot(new ApplyPowerAction(p, p, new AcceleratePower(p, accelAmt), accelAmt));
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
        return new QuantumTunnel();
    }
}
