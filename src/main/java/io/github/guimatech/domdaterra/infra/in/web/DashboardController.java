package io.github.guimatech.domdaterra.infra.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        definePieCharts(model);
        defineHorizontalBarChart(model);

        return "dashboard";
    }

    private void defineHorizontalBarChart(Model model) {
        var tasksWithoutDueDate = 3;
        var tasksWithoutPreviewDate = 4;
        var tasksWithoutResponsible = 144;
        var tasksWithoutStatus = 148;
        var tasksWithoutDescription = 9;
        var tasksWithoutNID = 2;

        model.addAttribute("tasksWithoutDueDate", tasksWithoutDueDate);
        model.addAttribute("tasksWithoutPreviewDate", tasksWithoutPreviewDate);
        model.addAttribute("tasksWithoutResponsible", tasksWithoutResponsible);
        model.addAttribute("tasksWithoutStatus", tasksWithoutStatus);
        model.addAttribute("tasksWithoutDescription", tasksWithoutDescription);
        model.addAttribute("tasksWithoutNID", tasksWithoutNID);
    }

    private void definePieCharts(Model model) {
        var internalTasksOnTime = 1;
        var internalTasksOutOfTime = 22;
        var stagnantInternalTasks = 3;

        var externalTasksOnTime = 0;
        var externalTasksOutOfTime = 8;
        var stagnantExternalTasks = 2;

        var tasksDone = 221;
        var tasksInProgress = 36;

        model.addAttribute("internalTasksOnTime", internalTasksOnTime);
        model.addAttribute("internalTasksOutOfTime", internalTasksOutOfTime);
        model.addAttribute("stagnantInternalTasks", stagnantInternalTasks);

        model.addAttribute("externalTasksOnTime", externalTasksOnTime);
        model.addAttribute("externalTasksOutOfTime", externalTasksOutOfTime);
        model.addAttribute("stagnantExternalTasks", stagnantExternalTasks);

        model.addAttribute("tasksDone", tasksDone);
        model.addAttribute("tasksInProgress", tasksInProgress);
    }
}