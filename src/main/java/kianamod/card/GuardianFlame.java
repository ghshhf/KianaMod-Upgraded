package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.ProtectionPower;
import kianamod.stances.FlameStance;

/**
 * 守护之焰 (GuardianFlame)
 * 获得 8(11) 点格挡与 1(2) 层庇护；若处于薪炎之律者形态，额外获得 4 点格挡。
 */
public class GuardianFlame extends CustomCard {
    public static final String ID = "ExampleMod:GuardianFlame";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/GuardianFlame.png";

    private static final int COST = 1;
    private static final int BASE_BLOCK = 8;
    private static final int UPG_BLOCK = 11;
    private static final int BASE_PROT = 1;
    private static final int UPG_PROT = 2;
    private static final int FLAME_BONUS = 4;

    public GuardianFlame() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BASE_BLOCK;
        this.baseMagicNumber = BASE_PROT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.magicNumber, false), this.magicNumber));
        if (p.stance instanceof FlameStance) {
            addToBot(new GainBlockAction(p, p, FLAME_BONUS));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPG_BLOCK - BASE_BLOCK);
            this.upgradeMagicNumber(UPG_PROT - BASE_PROT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuardianFlame();
    }
}
