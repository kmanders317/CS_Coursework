package utils;

import java.util.HashMap;
import java.util.Map;

public class NPCDialogue {
    Map<String, String> dialogue = new HashMap<>();

    public NPCDialogue() {
        // first npc
        dialogue.put("npc0", "Do you like to go rock climbing?");
        dialogue.put("pnpc0", "Você gosta de fazer a escalada?");
        dialogue.put("npc00", "Yes! I do it every day!");
        dialogue.put("pnpc00", "Sim! Eu fazo isso cada dia!");
        dialogue.put("npc00r", "That's amazing! We should go together sometime");
        dialogue.put("pnpc00r", "Legal, devemos ir juntos alguma vez!");
        dialogue.put("npc00r0", "Yes, I'd love to have a new climbing buddy");
        dialogue.put("pnpc00r0", "Sim, eu gostaria de ter um nuovo parceiro de escalada!");
        dialogue.put("npc00r1", "Yeah maybe sometime...");
        dialogue.put("pnpc00r1", "Sim talvez alguma vez...");
        dialogue.put("npc01", "I've never gone, but I'd love to try!");
        dialogue.put("pnpc01", "Eu nunca tem feito a escalada, mas gostaria muito de intentar!");
        dialogue.put("npc01r", "Did you know we have a rock wall, right here on campus? It's at the RSF");
        dialogue.put("pnpc01r", "Você sabe que temos uma parede de escalada, no campus? E no RSF");
        dialogue.put("npc01r0", "Yes I've heard of it, let's check it out together sometime");
        dialogue.put("pnpc01r0", "npc01r0");
        dialogue.put("npc01r1", "No, I didn't know that. How convenient!");
        dialogue.put("pnpc01r1", "Nao, eu nao sabia isso. Que conveniente!");
        dialogue.put("npc02", "No, who would do that?! That's scary!");
        dialogue.put("pnpc02", "Nao, quem faria isso?! No, who would do that? That's scary!");
        dialogue.put("npc02r", "Well for me, rock climbing helped me get over my fear of heights. For the most part at least...");
        dialogue.put("pnpc02r", "npc02r");
        dialogue.put("npc02r0", "I still would never do that...");
        dialogue.put("pnpc02r0", "npc02r0");
        dialogue.put("npc02r1", "Okay maybe sometime... I'm running late to my midterm, I need to go!");
        dialogue.put("pnpc02r1", "npc02r1");
        dialogue.put("npc0f", "Well, you have a wonderful day!");
        dialogue.put("ppnc0f", "Tem um bom dia!");

        // second npc
        dialogue.put("npc1", "Have you seen the monster yet?");
        dialogue.put("pnpc1", "");
        dialogue.put("npc10", "No, what monster?!?!");
        dialogue.put("pnpc10", "pnpc20");
        dialogue.put("npc10r", "npc20r");
        dialogue.put("pnpc10r", "npc20r");
        dialogue.put("npc10r0", "npc20r0");
        dialogue.put("pnpc10r0", "npc20r0");
        dialogue.put("npc10r1", "npc20r1");
        dialogue.put("pnpc10r1", "npc20r1");
        dialogue.put("npc11", "Yeah, what about it?");
        dialogue.put("pnpc11", "npc21");
        dialogue.put("npc11r", "npc21r");
        dialogue.put("pnpc11r", "npc21r");
        dialogue.put("npc11r0", "npc21r0");
        dialogue.put("pnpc11r0", "npc21r0");
        dialogue.put("npc11r1", "npc21r1");
        dialogue.put("pnpc11r1", "npc21r1");
        dialogue.put("npc12", "I don't believe in monsters.");
        dialogue.put("pnpc12", "npc22");
        dialogue.put("npc12r", "You will after you see this one!");
        dialogue.put("pnpc12r", "npc22r");
        dialogue.put("npc12r0", "npc22r0");
        dialogue.put("pnpc12r0", "npc22r0");
        dialogue.put("npc12r1", "npc22r1");
        dialogue.put("pnpc12r1", "npc22r1");
        dialogue.put("npc1f", "He's coming for us all!");
        dialogue.put("pnpc1f", "");

        // third npc
        dialogue.put("npc2", "Hey can you help me? My car broke down!");
        dialogue.put("pnpc2", "Voce pode me ajudar? Meu carro quebrou!");
        dialogue.put("npc20", "No, I don't have time!");
        dialogue.put("pnpc20", "Nao, nao tenho tempo.");
        dialogue.put("npc20r", "Wow, that's rude.");
        dialogue.put("pnpc20r", "npc20r");
        dialogue.put("npc20r0", "Tough luck buttercup.");
        dialogue.put("pnpc20r0", "npc20r0");
        dialogue.put("npc20r1", "I'm really sorry, I'm running late to my midterm!");
        dialogue.put("pnpc20r1", "npc20r1");
        dialogue.put("npc21", "Of course, what do you need me to do?");
        dialogue.put("pnpc21", "npc21");
        dialogue.put("npc21r", "Thank you so much! The engine just started smoking...");
        dialogue.put("pnpc21r", "npc21r");
        dialogue.put("npc21r0", "Uh oh, that sounds dangerous! We should run away!");
        dialogue.put("pnpc21r0", "npc21r0");
        dialogue.put("npc21r1", "Here, let me call my brother. He's a whiz with cars");
        dialogue.put("pnpc21r1", "npc21r1");
        dialogue.put("npc22", "Maybe, what do you want?");
        dialogue.put("pnpc22", "npc22");
        dialogue.put("npc22r", "Well, you see I ran out of gas...");
        dialogue.put("pnpc22r", "npc22r");
        dialogue.put("npc22r0", "Wow, you're still using a gas powered car? In 2024?");
        dialogue.put("pnpc22r0", "npc22r0");
        dialogue.put("npc22r1", "Let me call my friend, I'm sure they can grab some and head out here");
        dialogue.put("pnpc22r1", "npc22r1");
        dialogue.put("npc2f", "Beware the monster!!!!");
        dialogue.put("pnpc2f", "Cuidado com o monstro!!!!");

    }

    public Map<String, String> dialogue() {
        return dialogue;
    }
}
