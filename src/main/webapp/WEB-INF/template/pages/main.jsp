<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div class="row mt-3">
    <div class="col">
        <h2 class="title h2 text-center">Welcome</h2>
    </div>
</div>
<div class="row d-flex justify-content-center mt-3">
    We are pleased to welcome you to our knowledge testing application in the form of tests. Use the menu to navigate the app
</div>
<hr>
<div class="row">
    <p>There are 3 types of roles in the system: admin, tutor, user.
        A user can have one or several roles at once.
        Different functionality is available depending on the roles.</p>
</div>
<div class="row">
    <div class="col-md-4">
        <h3 class="">ADMIN</h3>
        <ul>
            <li>create new users in system;</li>
            <li>update user profiles;</li>
            <li>block/unblock users;</li>
            <li>change user roles;</li>
            <li>create, update topics;</li>
            <li>create, update tests;</li>
            <li>view all statistics:
                <ul>
                    <li>by questions</li>
                    <li>by users</li>
                    <li>by tests</li>
                    <li>personal</li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="col-md-4">
        <h3 class="">TUTOR</h3>
        <ul>
            <li>create, update questions</li>
            <li>view & update own profiles;</li>
            <li>view statistics:
                <ul>
                    <li>by questions</li>
                    <li>by tests</li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="col-md-4">
        <h3 class="">USER</h3>
        <ul>
            <li>take tests</li>
            <li>view & update own profiles;</li>
            <li>view own statistics</li>
        </ul>
    </div>
</div>


<div class="row">
    <div class="col">
        <table class="table table-hover table-sm">
            <thead class="special-color-dark white-text">
            <tr>
                <th>Function</th>
                <th class="w-20 text-center">Admin</th>
                <th class="w-20 text-center">Tutor</th>
                <th class="w-20 text-center">User</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Add, edit & delete a member</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">-</td>
                    <td class="w-20 text-center">-</td>
                </tr>
                <tr>
                    <td>Watch the member's progress grid</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">-</td>
                </tr>
                <tr>
                    <td>Watch the test detail page</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">+</td>
                </tr>
                <tr>
                    <td>Watch the member's test manage grid</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">-</td>
                </tr>
                <tr>
                    <td>Check the test's state as success or fail</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">-</td>
                </tr>
                <tr>
                    <td>Add, edit & delete a new test</td>
                    <td class="w-20 text-center">-</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">-</td>
                </tr>
                <tr>
                    <td>Add, edit & delete a topic</td>
                    <td class="w-20 text-center">+</td>
                    <td class="w-20 text-center">-</td>
                    <td class="w-20 text-center">-</td>
                </tr>
                <tr>
                    <td>Watch the after test review detail page</td>
                    <td class="w-20 text-center">-</td>
                    <td class="w-20 text-center">-</td>
                    <td class="w-20 text-center">+</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<hr>
<h4>Authors:</h4>
<ul>
    <li><a class="text-success" href="mailto: alexandr.heilash@gmail.com">Alexandr Heilash</a></li>
    <li><a class="text-success" href="mailto: yahor.radziuk@gmail.com">Yahor Radziuk</a></li>
    <li><a class="text-success" href="mailto: aleksandr.anashkevich@gmail.com">Aleksandr Anashkevich</a></li>
</ul>

The source code of the project is available on <a class="text-success" href="https://github.com/heilashaa/dits">github</a>


