[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                MagicTargetChoice.NEG_TARGET_ARTIFACT,
                MagicDestroyTargetPicker.Destroy,
                this,
                "Destroy target artifact.\$ " +
                "Scry 2."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                final MagicPermanent permanent ->
                game.doAction(new MagicDestroyAction(permanent));
                game.addEvent(new MagicScryXEvent(event.getSource(),event.getPlayer(),2));
            });
        }
    }
]
