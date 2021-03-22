def choice = new MagicTargetChoice("a nonland permanent to sacrifice");

def action = {
    final MagicGame game, final MagicEvent event ->
    if (event.isMode(1)) {
        game.doAction(new ChangeLifeAction(event.getPlayer(), -3));
    } else if (event.isMode(2)) {
        final MagicEvent sac = new MagicSacrificePermanentEvent(
            event.getPlayer(),
            choice
        );
        if (sac.isSatisfied()) {
            game.addEvent(sac);
        } else {
            game.doAction(new ChangeLifeAction(event.getPlayer(), -3));
        }
    } else if (event.isMode(3)) {
        final MagicEvent discard = new MagicDiscardEvent(event.getPlayer())
        if (discard.isSatisfied()) {
            game.addEvent(discard);
        } else {
            game.doAction(new ChangeLifeAction(event.getPlayer(), -3));
        }
    }
}

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                NEG_TARGET_CREATURE,
                this,
                "Put three -1/-1 counters on target creature\$. " +
                "Its controller loses 3 life unless he or she sacrifices a nonland permanent or discards a card."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                game.doAction(new ChangeCountersAction(event.getPlayer(), it, MagicCounterType.MinusOne, 3));
                game.addEvent(new MagicEvent(
                    event.getSource(),
                    it.getController(),
                    new MagicOrChoice(
                        MagicChoice.NONE,
                        MagicChoice.NONE,
                        MagicChoice.NONE
                    ),
                    action,
                    "Choose one\$ — (1) PN lose 3 life; " +
                    "or (2) PN sacrifices a nonland permanent; " +
                    "or (3) PN discards a card."
                ));
            });
        }
    }
]
