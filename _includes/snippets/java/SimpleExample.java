class State {
    private List<String> _alreadyGreeted = new LinkedList<>();

    public static State create() {
        return new State();
    }

    String greetings(String name){
        String greeting = "Hello " + name + "\nI already greeted all these guys: " + _alreadyGreeted;
        _alreadyGreeted.add(name);
        return greeting;
    }
}
