/*
 * Copyright (c) 2024 Erick Howard
 *
 * This file is part of SCIEToday.
 *
 * SCIEToday is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * SCIEToday is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with SCIEToday. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package dev.scie.today

object ModelJson {
    val assemblyJson = """
        [
          {
            "title": "A2 Form time 19 Aug 7.50 ",
            "location": "form rooms",
            "date": "2024-08-19",
            "classes": "A2.Wood3"
          }
        ]
    """.trimIndent()

    val attendanceJson = """
        {
          "student_name": "Erick",
          "student_id": 22901,
          "total_absent_count": 5.0,
          "record_of_days": [
            {
              "date": "2024-08-19",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 21451,
                  "course_name": "AS.B.FMA1",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 21451,
                  "course_name": "AS.B.FMA1",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 21471,
                  "course_name": "AL.C.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 21471,
                  "course_name": "AL.C.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 21819,
                  "course_name": "AL.D.PHY2",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 21819,
                  "course_name": "AL.D.PHY2",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2024-04-11",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "MR(07:50-07:57)",
                  "kind": 2,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18456,
                  "course_name": "A1.PE.TH5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18456,
                  "course_name": "A1.PE.TH5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.25
            },
            {
              "date": "2024-04-03",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19895,
                  "course_name": "A1.Wood3.PSHE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 1,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2024-04-02",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19625,
                  "course_name": "TUT.MAT.TU5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 6,
                  "reason": "A1 Photo taking"
                },
                {
                  "course_id": 18415,
                  "course_name": "A1.B.EPQ.PHY",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18415,
                  "course_name": "A1.B.EPQ.PHY",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2024-03-27",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "MR(07:50-07:57)",
                  "kind": 2,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19895,
                  "course_name": "A1.Wood3.PSHE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.25
            },
            {
              "date": "2024-03-19",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "school reason"
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19625,
                  "course_name": "TUT.MAT.TU5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18415,
                  "course_name": "A1.B.EPQ.PHY",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18415,
                  "course_name": "A1.B.EPQ.PHY",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2024-01-31",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19895,
                  "course_name": "A1.Wood3.PSHE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 1,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-11-29",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 4,
                  "reason": "Sick Leave"
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-11-16",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "teacher absent"
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18456,
                  "course_name": "A1.PE.TH5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18456,
                  "course_name": "A1.PE.TH5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-11-10",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 6,
                  "reason": "vaccination in school"
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-10-24",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Teacher absent"
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19625,
                  "course_name": "TUT.MAT.TU5",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18503,
                  "course_name": "AL.A.MAT1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18415,
                  "course_name": "A1.B.EPQ.PHY",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18415,
                  "course_name": "A1.B.EPQ.PHY",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-09-27",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19329,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 19643,
                  "course_name": "TUT.PHY.M5",
                  "kind": 6,
                  "reason": "timetable change"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-09-08",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Red rainstorm"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-09-01",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 18673,
                  "course_name": "AS.D.HIS",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 18692,
                  "course_name": "AS.E.PHY1",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 18703,
                  "course_name": "AS.F.CPU",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "typhoon"
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-05-03",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-04-19",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17728,
                  "course_name": "AS.C.CHE2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17728,
                  "course_name": "AS.C.CHE2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 6,
                  "reason": "Exam"
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 6,
                  "reason": "Exam"
                },
                {
                  "course_id": 17816,
                  "course_name": "G2.WOOD3.PSHE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17273,
                  "course_name": "G2.ENR.UCO2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17273,
                  "course_name": "G2.ENR.UCO2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-04-07",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Sports Day"
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2023-03-31",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17704,
                  "course_name": "G2.Z.PHY2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 5,
                  "reason": "visa issues"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 5,
                  "reason": "visa issues"
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 5,
                  "reason": "visa issues"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 5,
                  "reason": "visa issues"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 5,
                  "reason": "visa issues"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.5
            },
            {
              "date": "2023-02-24",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 1,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2022-12-20",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 6,
                  "reason": "Teacher Absent."
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 8,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2022-11-01",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17860,
                  "course_name": "G2.15A.CHI",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17860,
                  "course_name": "G2.15A.CHI",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17751,
                  "course_name": "G2.W.CPU2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 18214,
                  "course_name": "TUT.HIS.TU6",
                  "kind": 1,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17704,
                  "course_name": "G2.Z.PHY2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17704,
                  "course_name": "G2.Z.PHY2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17783,
                  "course_name": "G2.X.HIS1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17783,
                  "course_name": "G2.X.HIS1",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            },
            {
              "date": "2022-10-24",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17751,
                  "course_name": "G2.W.CPU2",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17751,
                  "course_name": "G2.W.CPU2",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17783,
                  "course_name": "G2.X.HIS1",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17783,
                  "course_name": "G2.X.HIS1",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17728,
                  "course_name": "AS.C.CHE2",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17728,
                  "course_name": "AS.C.CHE2",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 5,
                  "reason": "Visa application, ID Card or Passport Renew"
                }
              ],
              "absent_count": 1.0
            },
            {
              "date": "2022-10-21",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.5
            },
            {
              "date": "2022-10-14",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "Sick Leave"
                },
                {
                  "course_id": 17704,
                  "course_name": "G2.Z.PHY2",
                  "kind": 2,
                  "reason": "None"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.5
            },
            {
              "date": "2022-09-30",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "Sick Leave（go to the hospital）"
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "Sick Leave（go to the hospital）"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 4,
                  "reason": "Sick Leave（go to the hospital）"
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 4,
                  "reason": "Sick Leave（go to the hospital）"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 1.0
            },
            {
              "date": "2022-09-23",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "sick leave（go to hospital）"
                },
                {
                  "course_id": 17886,
                  "course_name": "G2.17.PE",
                  "kind": 4,
                  "reason": "sick leave（go to hospital）"
                },
                {
                  "course_id": 17704,
                  "course_name": "G2.Z.PHY2",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 4,
                  "reason": "go to clinic"
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 4,
                  "reason": "go to clinic"
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 1.0
            },
            {
              "date": "2022-08-24",
              "attendances": [
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17722,
                  "course_name": "AS.C.CHE1",
                  "kind": 6,
                  "reason": "Timetable Change"
                },
                {
                  "course_id": 17722,
                  "course_name": "AS.C.CHE1",
                  "kind": 6,
                  "reason": "Timetable Change"
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17284,
                  "course_name": "AS.D.MAT",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17816,
                  "course_name": "G2.WOOD3.PSHE",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17417,
                  "course_name": "G2.ENR.ENG3",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17417,
                  "course_name": "G2.ENR.ENG3",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 17561,
                  "course_name": "G2.15.ENG",
                  "kind": 0,
                  "reason": ""
                },
                {
                  "course_id": 0,
                  "course_name": "ES",
                  "kind": 0,
                  "reason": ""
                }
              ],
              "absent_count": 0.0
            }
          ]
        }
    """.trimIndent()

    val timetableJson = """
        {
          "week_type": "A",
          "week_a_periods": 41,
          "week_b_periods": 42,
          "duty_periods": 0,
          "contract_periods": 0,
          "weekdays": [
            {
              "periods": [
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17751,
                      "name": "Computer Science-G2.W.CPU2",
                      "room": "B433",
                      "teacher": "NAB",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17751,
                      "name": "Computer Science-G2.W.CPU2",
                      "room": "B433",
                      "teacher": "NAB",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17783,
                      "name": "History-G2.X.HIS1",
                      "room": "A315",
                      "teacher": "PHN",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17783,
                      "name": "History-G2.X.HIS1",
                      "room": "A315",
                      "teacher": "PHN",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A508",
                      "teacher": "JEC",
                      "week_type": "B"
                    },
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A509",
                      "teacher": "JEC",
                      "week_type": "A"
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17728,
                      "name": "Chemistry-AS.C.CHE2",
                      "room": "B812",
                      "teacher": "MNO",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17728,
                      "name": "Chemistry-AS.C.CHE2",
                      "room": "B812",
                      "teacher": "MNO",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17284,
                      "name": "Mathematics-AS.D.MAT",
                      "room": "B423",
                      "teacher": "BIW",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17284,
                      "name": "Mathematics-AS.D.MAT",
                      "room": "B423",
                      "teacher": "BIW",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                }
              ]
            },
            {
              "periods": [
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A511",
                      "teacher": "KAM",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A511",
                      "teacher": "KAM",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17860,
                      "name": "Chinese-G2.15A.CHI",
                      "room": "B530",
                      "teacher": "STZ",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17860,
                      "name": "Chinese-G2.15A.CHI",
                      "room": "B530",
                      "teacher": "STZ",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17751,
                      "name": "Computer Science-G2.W.CPU2",
                      "room": "B427",
                      "teacher": "JAZ",
                      "week_type": "A"
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17704,
                      "name": "Physics-G2.Z.PHY2",
                      "room": "A707",
                      "teacher": "MIF",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17704,
                      "name": "Physics-G2.Z.PHY2",
                      "room": "A707",
                      "teacher": "MIF",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17783,
                      "name": "History-G2.X.HIS1",
                      "room": "A315",
                      "teacher": "PHN",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17783,
                      "name": "History-G2.X.HIS1",
                      "room": "A315",
                      "teacher": "PHN",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                }
              ]
            },
            {
              "periods": [
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17728,
                      "name": "Chemistry-AS.C.CHE2",
                      "room": "A612",
                      "teacher": "MNO",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17728,
                      "name": "Chemistry-AS.C.CHE2",
                      "room": "A612",
                      "teacher": "MNO",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17284,
                      "name": "Mathematics-AS.D.MAT",
                      "room": "B423",
                      "teacher": "BIW",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17284,
                      "name": "Mathematics-AS.D.MAT",
                      "room": "B423",
                      "teacher": "BIW",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17816,
                      "name": "PSHE-G2.WOOD3.PSHE",
                      "room": "B424",
                      "teacher": "RYA",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17273,
                      "name": "UCO-G2.ENR.UCO2",
                      "room": "B534",
                      "teacher": "BER",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17273,
                      "name": "UCO-G2.ENR.UCO2",
                      "room": "B534",
                      "teacher": "BER",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A510",
                      "teacher": "JEC",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A510",
                      "teacher": "JEC",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                }
              ]
            },
            {
              "periods": [
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17860,
                      "name": "Chinese-G2.15A.CHI",
                      "room": "A509",
                      "teacher": "STZ",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17860,
                      "name": "Chinese-G2.15A.CHI",
                      "room": "A509",
                      "teacher": "STZ",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17751,
                      "name": "Computer Science-G2.W.CPU2",
                      "room": "B433",
                      "teacher": "NAB",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17751,
                      "name": "Computer Science-G2.W.CPU2",
                      "room": "B433",
                      "teacher": "NAB",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17783,
                      "name": "History-G2.X.HIS1",
                      "room": "A315",
                      "teacher": "PHN",
                      "week_type": "A"
                    },
                    {
                      "type": 1,
                      "id": 18233,
                      "name": "Tutorial-TUT.CHE.TH56B",
                      "room": "B814",
                      "teacher": "MNO",
                      "week_type": "B"
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 18233,
                      "name": "Tutorial-TUT.CHE.TH56B",
                      "room": "B814",
                      "teacher": "MNO",
                      "week_type": "B"
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17704,
                      "name": "Physics-G2.Z.PHY2",
                      "room": "A806",
                      "teacher": "MIF",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17704,
                      "name": "Physics-G2.Z.PHY2",
                      "room": "A806",
                      "teacher": "MIF",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17728,
                      "name": "Chemistry-AS.C.CHE2",
                      "room": "A613",
                      "teacher": "MNO",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17728,
                      "name": "Chemistry-AS.C.CHE2",
                      "room": "A613",
                      "teacher": "MNO",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                }
              ]
            },
            {
              "periods": [
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17284,
                      "name": "Mathematics-AS.D.MAT",
                      "room": "B423",
                      "teacher": "BIW",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17284,
                      "name": "Mathematics-AS.D.MAT",
                      "room": "B423",
                      "teacher": "BIW",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17886,
                      "name": "PE-G2.17.PE",
                      "room": "BBL",
                      "teacher": "DAF",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17886,
                      "name": "PE-G2.17.PE",
                      "room": "BBL",
                      "teacher": "DAF",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17704,
                      "name": "Physics-G2.Z.PHY2",
                      "room": "A805",
                      "teacher": "MIF",
                      "week_type": "B"
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A508",
                      "teacher": "KAM",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": [
                    {
                      "type": 1,
                      "id": 17561,
                      "name": "English-G2.15.ENG",
                      "room": "A508",
                      "teacher": "KAM",
                      "week_type": ""
                    }
                  ]
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                },
                {
                  "events": []
                }
              ]
            }
          ]
        }
    """.trimIndent()

    val userInformationJson = """
        {
          "has_more_info": false,
          "general_info": {
            "id": 22901,
            "name": "Erick Thor Howard",
            "en_name": "Erick",
            "full_name": "Erick Thor Howard",
            "form_group": "A2.Wood3",
            "photo": "https://www.alevel.com.cn/user/user_image/?token=CHivxvZMalzy3ePuUfygKzcimPwvsP/bJTSVTgxwbZo%3D%0A&iv=nu/0AbLLDSS6w5yE11je/Q%3D%3D%0A"
          },
          "basic_info": {
            "gender": "Male",
            "grade": "A2",
            "house": "Wood",
            "dormitory": "",
            "dormitory_kind": "Day Student",
            "enrollment": "2022.8",
            "mobile": "18502074971",
            "school_email": "s22901.howard@stu.scie.com.cn",
            "student_email": "<REDACTED>"
          },
          "more_info": null,
          "relatives": null
        }
    """.trimIndent()
}